package com.cibertec.market.controller;

import com.cibertec.market.dto.TransactionDTO;
import com.cibertec.market.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
