package com.cibertec.market.repository;

import com.cibertec.market.enums.DocumentTypeEnum;
import com.cibertec.market.model.DocumentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Long> {
    boolean existsByType(DocumentTypeEnum type);
}
