package com.cibertec.market.service.impl;

import com.cibertec.market.dto.ChargeRequestDTO;
import com.cibertec.market.dto.ChargeResponseDTO;
import com.cibertec.market.exception.BusinessException;
import com.cibertec.market.model.Charge;
import com.cibertec.market.repository.ChargeRepository;
import com.cibertec.market.repository.DebtRepository;
import com.cibertec.market.service.ChargeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargeServiceImpl implements ChargeService {
    private final ChargeRepository chargeRepository;
    private final DebtRepository debtRepository;

    public List<ChargeResponseDTO> getAll(){
        return chargeRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public ChargeResponseDTO save(ChargeRequestDTO chargeRequestDTO) {
        if (chargeRepository.existsByName(chargeRequestDTO.getName())) {
            throw new RuntimeException("El nombre fue registrado anteriormente");
        }
        Charge charge = Charge.builder()
                .name(chargeRequestDTO.getName())
                .description(chargeRequestDTO.getDescription())
                .build();
        return convertToResponseDTO(chargeRepository.save(charge));
    }

    public ChargeResponseDTO update(Long id, ChargeRequestDTO chargeRequestDTO) {
        if (!chargeRepository.existsById(id)) {
            throw new RuntimeException("El cargo no existe");
        }
        if (chargeRepository.existsByNameAndIdNot(chargeRequestDTO.getName(), id)) {
            throw new RuntimeException("El nombre ya está registrado");
        }
        Charge charge = Charge.builder()
                .id(id)
                .name(chargeRequestDTO.getName())
                .description(chargeRequestDTO.getDescription())
                .build();
        return convertToResponseDTO(chargeRepository.save(charge));
    }

    public void delete(Long id) {
        boolean existsInDebt = debtRepository.existsByChargeId(id);

        if (existsInDebt) {
            throw new BusinessException(
                    "No se puede eliminar el cargo porque está asociado a deudas"
            );
        }

        chargeRepository.deleteById(id);
    }

    public boolean existID(Long id) {
        return chargeRepository.existsById(id);
    }

    private ChargeResponseDTO convertToResponseDTO(Charge charge) {
        return ChargeResponseDTO.builder()
                .id(charge.getId())
                .name(charge.getName())
                .description(charge.getDescription())
                .build();
    }
}
