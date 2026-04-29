package com.cibertec.market.repository;

import com.cibertec.market.model.Debt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DebtRepository extends JpaRepository<Debt, Long> {
    List<Debt> findByStallIdOrderByYearDescMonthDesc(Long stallId);

    boolean existsByStallIdAndChargeIdAndYearAndMonth(Long stallId, Long chargeId, Integer year, Integer month);

    boolean existsByChargeId(Long chargeId);
}
