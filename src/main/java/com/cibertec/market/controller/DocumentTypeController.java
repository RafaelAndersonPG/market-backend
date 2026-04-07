package com.cibertec.market.controller;

import com.cibertec.market.model.DocumentType;
import com.cibertec.market.service.DocumentTypeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipo-documento")
@RequiredArgsConstructor
public class DocumentTypeController {

    private final DocumentTypeService documentTypeService;

    @GetMapping("/listar")
    public ResponseEntity<List<DocumentType>> listDocuments() {
        return ResponseEntity.ok(documentTypeService.getAllDocumentTypes());
    }

    @PostMapping("/crear")
    public ResponseEntity<String> crearDocumento(@RequestBody @Valid DocumentType documentType) {
        try {
            documentTypeService.saveDocumentType(documentType);
            return ResponseEntity.ok("Ok");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
