package com.cibertec.market.service.impl;

import com.cibertec.market.dto.TransactionDTO;
import com.cibertec.market.exception.ResourceNotFoundException;
import com.cibertec.market.model.Account;
import com.cibertec.market.model.Transaction;
import com.cibertec.market.repository.AccountRepository;
import com.cibertec.market.repository.TransactionRepository;
import com.cibertec.market.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final AccountRepository accountRepository;
    private final TransactionRepository transactionRepository;

    private static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

    @Override
    public List<TransactionDTO> getTransactionsByStall(Long stallId) {

        Account account = accountRepository.findByStallId(stallId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        return transactionRepository.findAllByAccountIdOrderByDateDesc(account.getId())
                .stream()
                .map(t -> new TransactionDTO(
                        t.getId(),
                        t.getAmount(),
                        t.getType().name(),
                        t.getDescription(),
                        t.getDate() != null ? t.getDate().format(FORMATTER) : null
                ))
                .toList();
    }
}
