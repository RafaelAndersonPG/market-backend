package com.cibertec.market.service.impl;

import com.cibertec.market.dto.TransactionDTO;
import com.cibertec.market.dto.TransactionRequestDTO;
import com.cibertec.market.enums.TransactionType;
import com.cibertec.market.exception.ResourceNotFoundException;
import com.cibertec.market.model.Account;
import com.cibertec.market.model.Transaction;
import com.cibertec.market.repository.AccountRepository;
import com.cibertec.market.repository.TransactionRepository;
import com.cibertec.market.service.TransactionService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public void registerTransaction(TransactionRequestDTO dto) {

        Account account = accountRepository.findById(dto.getAccountId())
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        Transaction transaction = Transaction.builder()
                .account(account)
                .amount(dto.getAmount())
                .type(dto.getType())
                .description(dto.getDescription())
                .date(LocalDateTime.now())
                .build();

        transactionRepository.save(transaction);

        if (dto.getType() == TransactionType.INCOME) {
            account.setBalance(account.getBalance().add(dto.getAmount()));
        } else {
            account.setBalance(account.getBalance().subtract(dto.getAmount()));
        }

        accountRepository.save(account);
    }
}
