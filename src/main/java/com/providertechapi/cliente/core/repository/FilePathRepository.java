package com.providertechapi.cliente.core.repository;

import com.providertechapi.cliente.domain.entity.FilePath;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilePathRepository extends JpaRepository<FilePath, Long> {
}