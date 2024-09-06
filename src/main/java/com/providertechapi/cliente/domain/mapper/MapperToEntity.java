package com.providertechapi.cliente.domain.mapper;

import com.providertechapi.cliente.domain.entity.AbstractEntity;

public interface MapperToEntity<E extends AbstractEntity> {
    E toEntity();
}