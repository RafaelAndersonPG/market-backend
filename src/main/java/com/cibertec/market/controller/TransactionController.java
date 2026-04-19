package com.cibertec.market.controller;

import com.cibertec.market.dto.TransactionRequestDTO;
import com.cibertec.market.dto.TransactionResponseDTO;
import com.cibertec.market.service.TransactionService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/traspaso")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping
    public ResponseEntity<List<TransactionResponseDTO>> read() {
        return ResponseEntity.ok(transactionService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid TransactionRequestDTO transactionRequestDTO) {
        try {
            return ResponseEntity.ok(transactionService.save(transactionRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
