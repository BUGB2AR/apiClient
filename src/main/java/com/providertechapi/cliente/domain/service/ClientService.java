package com.providertechapi.cliente.domain.service;

import com.providertechapi.cliente.domain.entity.ClientEntity;
import com.providertechapi.cliente.infra.service.AbstractService;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientService extends AbstractService<ClientEntity, Long> {
    void validateCpfClient(String cpfPerson);
    void isthereAreRegisteredCpf(String cpf);
    void isthereAreRegisteredEmail(String email);
    List<ClientEntity> findByNameContaining(@Param("name") String name);
}
