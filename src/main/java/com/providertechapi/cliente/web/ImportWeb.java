package com.providertechapi.cliente.web;

import com.providertechapi.cliente.core.api.resource.ProcessorApi;
import com.providertechapi.cliente.core.api.swagger.CsvProcessorApiSwagger;
import com.providertechapi.cliente.domain.service.impl.CsvImportServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.io.IOException;

@RestController
public class ImportWeb implements CsvProcessorApiSwagger, ProcessorApi {

    private final CsvImportServiceImpl csvImportService;

    public ImportWeb(CsvImportServiceImpl csvImportService) {
        this.csvImportService = csvImportService;
    }

    @Override
    public ResponseEntity<String> processFile(@RequestParam String filePath) throws IOException {
        csvImportService.processFile(filePath);
        return ResponseEntity.status(HttpStatus.OK).body("File processed and data saved successfully!");
    }
}
