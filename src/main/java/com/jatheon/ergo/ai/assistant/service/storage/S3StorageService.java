package com.jatheon.ergo.ai.assistant.service.storage;

import com.jatheon.ergo.ai.assistant.model.storage.DocumentMetadata;
import com.jatheon.ergo.ai.assistant.model.storage.StorageFile;
import com.jatheon.ergo.ai.assistant.service.error.StorageException;
import com.jatheon.ergo.ai.assistant.service.storage.parser.DocumentParserFactory;
import com.jatheon.ergo.ai.assistant.service.util.PagingRequest;
import com.jatheon.ergo.ai.assistant.service.util.PagingResponse;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.DocumentParser;
import dev.langchain4j.data.document.loader.amazon.s3.AmazonS3DocumentLoader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.HeadObjectRequest;
import software.amazon.awssdk.services.s3.model.HeadObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.jatheon.ergo.ai.assistant.service.error.StorageException.UNABLE_TO_READ_FOR_BUCKET;
import static com.jatheon.ergo.ai.assistant.service.error.StorageException.UNABLE_TO_READ_FOR_BUCKET_AND_LOCATION;
import static com.jatheon.ergo.ai.assistant.service.error.StorageException.UNABLE_TO_STORE_FILE;
import static com.jatheon.ergo.ai.assistant.service.util.ClearDataUtil.clearData;
import static java.lang.String.format;

@Slf4j
@RequiredArgsConstructor
public class S3StorageService implements StorageService {

    @Value("${aws.s3.bucketName}")
    private String bucketName;

    private final S3Client s3Client;
    private final DocumentParserFactory documentParserFactory;
    private final AmazonS3DocumentLoader documentLoader;

    @Override
    public void uploadFile(final MultipartFile file, final String fileName) throws StorageException {
        try {
            PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                    .bucket(bucketName)
                    .key(fileName)
                    .build();
            s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
            log.info("File uploaded successfully to S3: {}", fileName);
        } catch (S3Exception | IOException ex) {
            throw new StorageException(format(UNABLE_TO_STORE_FILE, bucketName, fileName), ex);
        }
    }

    /**
     * TODO: we have to better organize this Exceptions
     * @param location
     * @return
     */
    @Override
    public DocumentMetadata fetchMetadata(final String location) {
        log.debug("loading object metadata [location: {}]", location);
        try {
            HeadObjectRequest headObjectRequest = HeadObjectRequest.builder()
                    .bucket(bucketName)
                    .key(location)
                    .build();
            HeadObjectResponse headObjectResponse = s3Client.headObject(headObjectRequest);
            return DocumentMetadata.of(headObjectResponse.contentLength(), headObjectResponse.contentType(), headObjectResponse.eTag());
        } catch(final S3Exception s3Exception) {
            if (s3Exception.statusCode() == HttpStatus.NOT_FOUND.value()) {
                throw new IllegalArgumentException("Content with specified key does not exist.", s3Exception);
            }
            throw new IllegalArgumentException(s3Exception);
        } catch (final Exception exception) {
            throw new IllegalArgumentException("Unable to read message content from storage.", exception);
        }

    }

    @Override
    public Document load(final String location) {
        try {
            DocumentMetadata attachmentMetadata = fetchMetadata(location);
            log.debug("AttachmentMetadata [contentType: {}, contentLength:{}, eTag: {}]", attachmentMetadata.getContentType(),
                    attachmentMetadata.getContentLength(),
                    attachmentMetadata.getETag());
            DocumentParser documentParser = documentParserFactory.create(attachmentMetadata.getContentType());
            Document document = documentLoader.loadDocument(bucketName, location, documentParser);
            document.metadata().put("contentLength", attachmentMetadata.getContentLength());
            document.metadata().put("contentType", attachmentMetadata.getContentType());
            document.metadata().put("eTag", clearData(attachmentMetadata.getETag()));
            return document;
        } catch(final S3Exception s3Exception) {
            throw new StorageException(format(UNABLE_TO_READ_FOR_BUCKET_AND_LOCATION, bucketName, location), s3Exception);
        }
    }

    @Override
    public PagingResponse<StorageFile> fetchAll(final PagingRequest pagingRequest) {
        List<StorageFile> storageFileList = new ArrayList<>();
        ListObjectsV2Request  listObjectsV2Request = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();
        try {
            s3Client.listObjectsV2(listObjectsV2Request).contents().forEach(s3Object -> {
                storageFileList.add(StorageFile.of(bucketName, s3Object.key(), s3Object.lastModified(), s3Object.size(), s3Object.eTag()));
            });
            return new PagingResponse<>(storageFileList, storageFileList.size(), pagingRequest.getPage(), pagingRequest.getSize(), storageFileList.size());
        } catch (final S3Exception s3Exception) {
            throw new StorageException(format(UNABLE_TO_READ_FOR_BUCKET, bucketName), s3Exception);
        }
    }

}
