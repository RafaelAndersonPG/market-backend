package com.cibertec.market.repository;

import com.cibertec.market.model.AccountSequence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountSequenceRepository extends JpaRepository<AccountSequence, Long> {
}
