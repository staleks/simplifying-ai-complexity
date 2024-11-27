package com.jatheon.ergo.ai.assistant.service.storage;

import com.jatheon.ergo.ai.assistant.model.storage.DocumentMetadata;
import com.jatheon.ergo.ai.assistant.model.storage.StorageFile;
import dev.langchain4j.data.document.Document;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface StorageService {

    void uploadFile(final MultipartFile file, final String fileName) throws IOException;

    DocumentMetadata fetchMetadata(final String location);

    Document load(final String location);

    List<StorageFile> fetchAll();

}
