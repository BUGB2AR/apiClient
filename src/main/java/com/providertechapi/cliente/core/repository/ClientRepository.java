package com.providertechapi.cliente.core.repository;

import com.providertechapi.cliente.domain.entity.ClientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.List;

public interface ClientRepository extends JpaRepository<ClientEntity, Long> {

    @Query("SELECT CASE WHEN COUNT(pe) > 0 THEN TRUE ELSE FALSE END FROM ClientEntity pe WHERE pe.cpf = ?1")
    boolean isthereAreRegisteredCpf(String cpf);

    @Query("SELECT CASE WHEN COUNT(pe) > 0 THEN TRUE ELSE FALSE END FROM ClientEntity pe WHERE pe.cpf = ?1")
    boolean isthereAreRegisteredEmail(String email);

    @Query("SELECT c FROM ClientEntity c WHERE c.name LIKE %:name%")
    List<ClientEntity> findByNameContaining(@Param("name") String name);
}
