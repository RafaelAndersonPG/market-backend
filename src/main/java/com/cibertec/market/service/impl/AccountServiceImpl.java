package com.cibertec.market.service.impl;

import com.cibertec.market.dto.AccountDTO;
import com.cibertec.market.exception.ResourceNotFoundException;
import com.cibertec.market.model.Account;
import com.cibertec.market.repository.AccountRepository;
import com.cibertec.market.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    @Override
    public AccountDTO getAccountByStallId(Long stallId) {

        Account account = accountRepository.findByStallId(stallId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        return new AccountDTO(
                account.getId(),
                account.getNumber(),
                account.getBalance()
        );
    }
}
