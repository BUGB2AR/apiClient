package com.providertechapi.cliente.domain.service.impl;

import com.providertechapi.cliente.core.repository.ClientRepository;
import com.providertechapi.cliente.domain.entity.ClientEntity;
import com.providertechapi.cliente.domain.exception.notification.NotificationException;
import com.providertechapi.cliente.domain.service.ClientService;
import com.providertechapi.cliente.domain.validation.CpfValidator;
import com.providertechapi.cliente.infra.service.AbstractServiceImpl;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ClientServiceImpl extends AbstractServiceImpl<ClientEntity, Long> implements ClientService {

    private final ClientRepository clientRepository;

    public ClientServiceImpl(JpaRepository<ClientEntity, Long> repository, ClientRepository clientRepository) {
        super(repository);
        this.clientRepository = clientRepository;
    }

    @Override
    public void validateCpfClient(String cpfPerson) {
        if (!CpfValidator.isValidCPF(cpfPerson)) throw new NotificationException("O CPF informado é inválido.");
    }

    @Override
    public void isthereAreRegisteredCpf(String cpf) {
        if (clientRepository.isthereAreRegisteredCpf(cpf)) {
            throw new NotificationException("O cpf Nao pode ser cadastrado no sistema pois ja existe.");
        }
    }

    @Override
    public void isthereAreRegisteredEmail(String email) {
        if (clientRepository.isthereAreRegisteredEmail(email)) {
            throw new NotificationException("O email Nao pode ser cadastrado no sistema pois ja existe.");
        }
    }

    @Override
    public List<ClientEntity> findByNameContaining(String name) {
        return clientRepository.findByNameContaining(name);
    }
}
