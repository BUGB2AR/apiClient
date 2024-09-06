package com.providertechapi.cliente.core.api.resource;

import com.providertechapi.cliente.infra.api.ProcessorCsvApi;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/import")
public interface ProcessorApi extends ProcessorCsvApi<String> {
}
