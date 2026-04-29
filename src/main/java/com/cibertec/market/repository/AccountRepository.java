package com.cibertec.market.repository;

import com.cibertec.market.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {

    Optional<Account> findByStallId(Long id);
}
