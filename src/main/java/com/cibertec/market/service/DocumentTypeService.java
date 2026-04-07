package com.cibertec.market.service;

import com.cibertec.market.model.DocumentType;

import java.util.List;

public interface DocumentTypeService {
    List<DocumentType> getAllDocumentTypes();

    void saveDocumentType(DocumentType documentType);
}
