package com.cibertec.market.controller;

import com.cibertec.market.dto.DebtRequestDTO;
import com.cibertec.market.dto.DebtResponseDTO;
import com.cibertec.market.service.DebtsService;
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
    private final DebtsService debtsService;

    @GetMapping
    public ResponseEntity<List<DebtResponseDTO>> getAll() {
        return ResponseEntity.ok(debtsService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody DebtRequestDTO debtRequestDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(debtsService.save(debtRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/pendientes/{stallId}")
    public ResponseEntity<List<DebtResponseDTO>> getPending(@PathVariable Long stallId) {
        return ResponseEntity.ok(debtsService.getPendingDebtsByStall(stallId));
    }

}
