package com.providertechapi.cliente.core.api.swagger;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

public interface CsvProcessorApiSwagger {

    @Operation(summary = "Process CSV file", description = "Processes a CSV file containing client data.")
    @ApiResponse(responseCode = "200", description = "Successfully processed the file and saved data")
    @ApiResponse(responseCode = "400", description = "Invalid file path or file format")
    ResponseEntity<String> processFile(
            @Parameter(description = "Path to the CSV file") @RequestParam String filePath) throws IOException;
}
