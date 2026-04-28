package com.cibertec.market.service;

import com.cibertec.market.dto.DebtRequestDTO;
import com.cibertec.market.dto.DebtResponseDTO;
import com.cibertec.market.model.Charge;
import com.cibertec.market.model.Debt;
import com.cibertec.market.model.Stall;
import com.cibertec.market.repository.ChargeRepository;
import com.cibertec.market.repository.DebtRepository;
import com.cibertec.market.repository.StallRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DebtService {
    private final DebtRepository debtRepository;
    private final StallRepository stallRepository;
    private final ChargeRepository chargeRepository;

    public List<DebtResponseDTO> getAll() {
        return debtRepository.findAll().stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional
    public DebtResponseDTO save(DebtRequestDTO debtRequestDTO) {
        boolean exists = debtRepository.existsByStallIdAndChargeIdAndYearAndMonth(
                debtRequestDTO.getStallId(),
                debtRequestDTO.getChargeId(),
                debtRequestDTO.getYear(),
                debtRequestDTO.getMonth());
        if (exists) {
            throw new RuntimeException("Ya existe una deuda de este tipo para el puesto en el periodo seleccionado.");
        }
        Stall stall = stallRepository.findById(debtRequestDTO.getStallId())
                .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
        Charge charge = chargeRepository.findById(debtRequestDTO.getChargeId())
                .orElseThrow(() -> new RuntimeException("Cargo no encontrado"));
        Debt debt = Debt.builder()
                .stall(stall)
                .charge(charge)
                .year(debtRequestDTO.getYear())
                .month(debtRequestDTO.getMonth())
                .amount(charge.getAmount())
                .description(charge.getName())
                .paid(false)
                .build();
        return convertToResponseDTO(debtRepository.save(debt));
    }

    public List<DebtResponseDTO> getPendingDebtsByStall(Long stallId) {
        return debtRepository.findByStallIdAndPaidFalse(stallId).stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    private DebtResponseDTO convertToResponseDTO(Debt debt) {
        return DebtResponseDTO.builder()
                .id(debt.getId())
                .stallName(debt.getStall().getName())
                .chargeName(debt.getCharge().getName())
                .amount(debt.getAmount())
                .period(debt.getMonth() + "/" + debt.getYear())
                .paid(debt.getPaid())
                .build();
    }
}
