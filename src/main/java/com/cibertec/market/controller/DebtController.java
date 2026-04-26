package com.cibertec.market.controller;

import com.cibertec.market.dto.DebtRequestDTO;
import com.cibertec.market.dto.DebtResponseDTO;
import com.cibertec.market.service.DebtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/deuda")
@RequiredArgsConstructor
public class DebtController {
    private final DebtService debtService;

    @GetMapping
    public ResponseEntity<List<DebtResponseDTO>> getAll() {
        return ResponseEntity.ok(debtService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DebtRequestDTO debtRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(debtService.save(debtRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pendientes/{stallId}")
    public ResponseEntity<List<DebtResponseDTO>> getPending(@PathVariable Long stallId) {
        return ResponseEntity.ok(debtService.getPendingDebtsByStall(stallId));
    }

}
