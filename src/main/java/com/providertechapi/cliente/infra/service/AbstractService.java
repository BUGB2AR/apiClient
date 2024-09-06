package com.providertechapi.cliente.infra.service;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface AbstractService<T, ID> {

    T save(T entity);

    void deleteById(ID id);

    T findById(ID id);

    List<T> findAll();

    T update(T entity);
}
