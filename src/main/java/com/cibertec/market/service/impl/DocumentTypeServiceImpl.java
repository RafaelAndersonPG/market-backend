package com.cibertec.market.service.impl;

import com.cibertec.market.model.DocumentType;
import com.cibertec.market.repository.DocumentTypeRepository;
import com.cibertec.market.service.DocumentTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentTypeServiceImpl implements DocumentTypeService {

    private final DocumentTypeRepository documentTypeRepository;

    @Override
    public List<DocumentType> getAllDocumentTypes() {
        return documentTypeRepository.findAll();
    }

    @Override
    public void saveDocumentType(DocumentType documentType) {
        if (documentTypeRepository.existsByType(documentType.getType())) {
            throw new RuntimeException("El tipo de documento ya existe");
        }

        documentTypeRepository.save(documentType);
    }
}
