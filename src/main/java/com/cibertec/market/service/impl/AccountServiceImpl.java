package com.cibertec.market.service.impl;

import com.cibertec.market.dto.AccountDTO;
import com.cibertec.market.exception.BusinessException;
import com.cibertec.market.exception.ResourceNotFoundException;
import com.cibertec.market.model.Account;
import com.cibertec.market.model.Stall;
import com.cibertec.market.repository.AccountRepository;
import com.cibertec.market.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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

    @Override
    public void createAccountForStall(Stall stall, Long number) {

        Account account = new Account();
        account.setNumber(String.valueOf(number));
        account.setBalance(BigDecimal.ZERO);
        account.setStall(stall);

        accountRepository.save(account);
    }

    @Override
    public void deleteByStallId(Long stallId) {

        Account account = accountRepository.findByStallId(stallId)
                .orElseThrow(() -> new ResourceNotFoundException("Cuenta no encontrada"));

        if (account.getBalance() != null && account.getBalance().compareTo(BigDecimal.ZERO) > 0) {
            throw new BusinessException("No se puede eliminar la cuenta porque tiene saldo");
        }

        accountRepository.delete(account);
    }
}
