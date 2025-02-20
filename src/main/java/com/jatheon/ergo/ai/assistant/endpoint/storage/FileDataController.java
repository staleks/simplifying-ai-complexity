package com.jatheon.ergo.ai.assistant.endpoint.storage;

import com.jatheon.ergo.ai.assistant.model.datatables.DataTablesInput;
import com.jatheon.ergo.ai.assistant.model.datatables.DataTablesOutput;
import com.jatheon.ergo.ai.assistant.model.datatables.OrderParameter;
import com.jatheon.ergo.ai.assistant.model.storage.StorageFile;
import com.jatheon.ergo.ai.assistant.service.error.StorageException;
import com.jatheon.ergo.ai.assistant.service.storage.StorageService;
import com.jatheon.ergo.ai.assistant.service.util.PagingRequest;
import com.jatheon.ergo.ai.assistant.service.util.PagingResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@Slf4j
@RestController
@RequiredArgsConstructor
public class FileDataController {

    private static final String FILES_DATATABLES_ENDPOINT =
            "/datatables/storage/v1/files";

    private final StorageService storageService;

    @GetMapping(value = FILES_DATATABLES_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DataTablesOutput<StorageFile>> listFiles(@Valid DataTablesInput dataTablesInput) {
        log.debug("listFiles - dataTablesInput: {}", dataTablesInput);
        try {
            PagingResponse<StorageFile> storageFilePagingResponse = storageService.fetchAll(getPagingRequest(
                    dataTablesInput.getPage(),
                    dataTablesInput.getLength(),
                    dataTablesInput.getOrder()));
            return ok(DataTablesOutput.of(
                    dataTablesInput.getDraw(),
                    storageFilePagingResponse.getTotal(),
                    storageFilePagingResponse.getTotal(),
                    storageFilePagingResponse.getItems()));
        } catch (final StorageException storageException) {
            log.error("Failed to fetch files from bucket!", storageException);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private PagingRequest getPagingRequest(
            final Integer page, final Integer size, final List<OrderParameter> orders) {
        log.debug("page: {}, size: {}", page, size);
        String sort;
        OrderParameter orderParameter = orders.getFirst();
        if (orderParameter.getColumn() == 0) {
            if (orderParameter.getDir().equals("asc")) {
                sort = "name";
            } else {
                sort = "-name";
            }
        } else {
            sort = "name";
        }
        return PagingRequest.of(page, size, sort);
    }


}
