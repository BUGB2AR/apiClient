package com.providertechapi.cliente.core.request;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ClientRequest {
    private String name;
    private String cpf;
    private String email;
    private LocalDate birthDate;
}
