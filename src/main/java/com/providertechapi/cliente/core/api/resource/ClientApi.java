package com.providertechapi.cliente.core.api.resource;

import com.providertechapi.cliente.core.request.ClientRequest;
import com.providertechapi.cliente.core.response.ClientResponse;
import com.providertechapi.cliente.infra.api.Api;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/clients")
public interface ClientApi extends Api<ClientRequest, ClientResponse> {}