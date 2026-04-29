package com.cibertec.market.service;

import com.cibertec.market.dto.AccountDTO;
import com.cibertec.market.model.Stall;

public interface AccountService {

    AccountDTO getAccountByStallId(Long stallId);

    void createAccountForStall(Stall stall, Long number);

    void deleteByStallId(Long accountId);
}