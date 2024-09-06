package com.providertechapi.cliente.core.response;

import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class ClientResponse {
    private Long id;
    private String name;
    private String cpf;
    private String email;
    private LocalDate birthDate;
}
