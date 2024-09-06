package com.providertechapi.cliente.infra.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.io.IOException;

public interface ProcessorCsvApi<RESPONSE>{

    @PostMapping("/process")
    ResponseEntity<RESPONSE> processFile(@RequestParam String filePath) throws IOException;
}
