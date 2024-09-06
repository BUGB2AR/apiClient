package com.providertechapi.cliente.web;

import com.providertechapi.cliente.core.api.swagger.ApiSwagger;
import com.providertechapi.cliente.core.api.resource.ClientApi;
import com.providertechapi.cliente.core.mapper.ClientMapper;
import com.providertechapi.cliente.core.request.ClientRequest;
import com.providertechapi.cliente.core.response.ClientResponse;
import com.providertechapi.cliente.domain.entity.ClientEntity;
import com.providertechapi.cliente.domain.model.Client;
import com.providertechapi.cliente.domain.service.ClientService;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ClientWeb implements ClientApi, ApiSwagger {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    public ClientWeb(ClientService clientService, ClientMapper clientMapper) {
        this.clientService = clientService;
        this.clientMapper = clientMapper;
    }

    @Override
    public ResponseEntity<ClientResponse> add(ClientRequest requestBody) {
        Client client = clientMapper.toDomain(requestBody);

        clientService.validateCpfClient(client.getCpf());
        clientService.isthereAreRegisteredCpf(client.getCpf());
        clientService.isthereAreRegisteredEmail(client.getEmail());

        ClientEntity entity = clientService.save(client.toEntity());
        ClientResponse response = clientMapper.toResponse(entity);

        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ClientResponse>> findAll() {
        List<ClientEntity> entities = clientService.findAll();
        return ResponseEntity.ok(clientMapper.toCollections(entities));
    }

    @Override
    public ResponseEntity<ClientResponse> findById(Long id) {
        ClientEntity entity = clientService.findById(id);
        if (entity == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(clientMapper.toResponse(entity));
    }

    @Override
    public void delete(Long id) {
        clientService.deleteById(id);
    }

    @Override
    public ResponseEntity<ClientResponse> update(Long id, ClientRequest requestBody) {

        Client client = clientMapper.toDomain(requestBody);
        ClientEntity existingEntity = clientService.findById(id);

        if (existingEntity == null) {
            return ResponseEntity.notFound().build();
        }

        if (!existingEntity.getCpf().equals(client.getCpf())) {
            clientService.validateCpfClient(client.getCpf());
        }

        if (!existingEntity.getEmail().equals(client.getEmail())) {
            clientService.isthereAreRegisteredCpf(client.getCpf());
        }

        BeanUtils.copyProperties(client, existingEntity, "id");
        ClientEntity updatedEntity = clientService.save(existingEntity);

        ClientResponse response = clientMapper.toResponse(updatedEntity);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<ClientResponse>> findByFilter(String filter) {
        return ResponseEntity.ok(clientMapper.toCollections(clientService.findByNameContaining(filter)));
    }
}