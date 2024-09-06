package com.providertechapi.cliente.domain.mapper;

import com.providertechapi.cliente.domain.entity.AbstractEntity;
import java.util.List;

public interface MapperToResponse<R, D extends AbstractEntity> {
    R toResponse(D entity);
    List<R> toCollections(List<D> list);
}