package com.providertechapi.cliente.core.mapper;

import com.providertechapi.cliente.core.request.ClientRequest;
import com.providertechapi.cliente.core.response.ClientResponse;
import com.providertechapi.cliente.domain.entity.ClientEntity;
import com.providertechapi.cliente.domain.mapper.MapperToDomain;
import com.providertechapi.cliente.domain.mapper.MapperToResponse;
import com.providertechapi.cliente.domain.model.Client;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientMapper implements MapperToDomain<Client, ClientRequest>, MapperToResponse<ClientResponse, ClientEntity> {
    @Override
    public Client toDomain(ClientRequest request) {
        Client client = new Client();
        client.setCpf(request.getCpf());
        client.setName(request.getName());
        client.setEmail(request.getEmail());
        client.setBirthDate(request.getBirthDate());
        return client;
    }

    @Override
    public ClientResponse toResponse(ClientEntity entity) {
        ClientResponse response = new ClientResponse();
        response.setId(entity.getId());
        response.setName(entity.getName());
        response.setEmail(entity.getEmail());
        response.setCpf(entity.getCpf());
        response.setBirthDate(entity.getBirthDate());

        return response;
    }

    public ClientEntity toEntity(Client client) {
        ClientEntity entity = new ClientEntity();
        entity.setName(client.getName());
        entity.setCpf(client.getCpf());
        entity.setEmail(client.getEmail());
        entity.setBirthDate(client.getBirthDate());
        return entity;
    }

    @Override
    public List<ClientResponse> toCollections(List<ClientEntity> list) {
        return list.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }
}
