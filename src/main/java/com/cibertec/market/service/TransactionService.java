package com.cibertec.market.service;

import com.cibertec.market.dto.TransactionDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionDTO> getTransactionsByStall(Long stallId);
}
