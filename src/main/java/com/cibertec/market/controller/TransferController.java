package com.cibertec.market.controller;

import com.cibertec.market.dto.TransferRequestDTO;
import com.cibertec.market.dto.TransferResponseDTO;
import com.cibertec.market.service.TransferService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/traspaso")
public class TransferController {
    private final TransferService transferService;

    @GetMapping
    public ResponseEntity<List<TransferResponseDTO>> read() {
        return ResponseEntity.ok(transferService.getAll());
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody @Valid TransferRequestDTO transferRequestDTO) {
        try {
            return ResponseEntity.ok(transferService.save(transferRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
