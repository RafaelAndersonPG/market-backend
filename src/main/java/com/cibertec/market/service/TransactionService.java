package com.cibertec.market.service;

import com.cibertec.market.dto.TransactionDTO;
import com.cibertec.market.dto.TransactionRequestDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getTransactionsByStall(Long stallId);
    void registerTransaction(TransactionRequestDTO dto);
}
