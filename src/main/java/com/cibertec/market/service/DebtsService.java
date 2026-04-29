package com.cibertec.market.service;

import com.cibertec.market.dto.DebtRequestDTO;
import com.cibertec.market.dto.DebtResponseDTO;

import java.util.List;

public interface DebtsService {

    List<DebtResponseDTO> getAll();
    DebtResponseDTO save(DebtRequestDTO debtRequestDTO);
    List<DebtResponseDTO> getPendingDebtsByStall(Long stallId);
}
