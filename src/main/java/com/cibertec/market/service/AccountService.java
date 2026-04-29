package com.cibertec.market.service;

import com.cibertec.market.dto.AccountDTO;

public interface AccountService {

    AccountDTO getAccountByStallId(Long stallId);
}