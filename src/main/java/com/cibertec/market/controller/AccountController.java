package com.cibertec.market.controller;

import com.cibertec.market.dto.AccountDTO;
import com.cibertec.market.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cuenta")
public class AccountController {
    private final AccountService accountService;

    @GetMapping("/stall/{stallId}")
    public ResponseEntity<AccountDTO> getByStallId(@PathVariable Long stallId) {

        AccountDTO account = accountService.getAccountByStallId(stallId);

        return ResponseEntity.ok(account);
    }
}
