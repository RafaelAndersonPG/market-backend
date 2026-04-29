package com.cibertec.market.controller;

import com.cibertec.market.dto.TransactionDTO;
import com.cibertec.market.dto.TransactionRequestDTO;
import com.cibertec.market.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/transaccion")
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping("/{id}/stall")
    public ResponseEntity<List<TransactionDTO>> getTransactions(@PathVariable Long id) {
        return ResponseEntity.ok(transactionService.getTransactionsByStall(id));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody TransactionRequestDTO dto) {
        transactionService.registerTransaction(dto);
        return ResponseEntity.ok().build();
    }
}
