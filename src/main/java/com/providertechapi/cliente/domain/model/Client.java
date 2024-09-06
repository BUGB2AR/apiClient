package com.providertechapi.cliente.domain.model;

import com.providertechapi.cliente.domain.entity.ClientEntity;
import com.providertechapi.cliente.domain.exception.notification.NotificationException;
import com.providertechapi.cliente.domain.exception.severity.Severity;
import com.providertechapi.cliente.domain.mapper.MapperToEntity;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import java.time.LocalDate;

@Getter
@Setter
public class Client implements MapperToEntity<ClientEntity> {

    private Long id;
    private String name;
    private String cpf;
    private String email;
    private LocalDate birthDate;

    public void setId(Long id) {
        if (id == null) throw new NotificationException("Id do cliente não pode ser nulo.", Severity.ERROR);
        this.id = id;
    }

    public void setName(String name){
        if (StringUtils.isBlank(name)) throw new NotificationException("Nome deve ser informado.", Severity.ERROR);
        if (StringUtils.equalsAnyIgnoreCase(name,"undefined")) throw new NotificationException("Campo de nome informado inadequadamente.", Severity.ERROR);
        this.name = name;
    }

    public void setEmail(String email) {
        if (StringUtils.isBlank(email)) throw new NotificationException("Email deve ser informado");
        if (StringUtils.equalsIgnoreCase(email, "undefined")) throw new NotificationException("Email invalido....");
        this.email = email;
    }

    public void setCpf(String cpf) {
        if (StringUtils.isBlank(cpf)) throw new NotificationException("Cpf deve ser informado.....");
        if (StringUtils.equalsIgnoreCase(cpf, "undefined")) throw new NotificationException("Cpf invalido....");
        this.cpf = cpf;
    }

    public void setBirthDate(LocalDate dataNascimento) {
        if (dataNascimento == null) throw new NotificationException("Data de nascimento deve ser informado.....", Severity.ERROR);
        if (dataNascimento.isAfter(LocalDate.now())) throw new NotificationException("Data de nascimento não pode ser uma data futura....", Severity.ERROR);
        this.birthDate = dataNascimento;
    }

    @Override
    public ClientEntity toEntity() {
        ClientEntity clientEntity = new ClientEntity();
        clientEntity.setId(this.getId());
        clientEntity.setCpf(this.getCpf());
        clientEntity.setBirthDate(this.getBirthDate());
        clientEntity.setEmail(this.getEmail());
        clientEntity.setName(this.getName());
        return clientEntity;
    }
}
