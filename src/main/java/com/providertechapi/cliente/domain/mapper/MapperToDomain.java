package com.providertechapi.cliente.domain.mapper;

public interface MapperToDomain<D, E> {
    D toDomain(E request);
}