package com.cibertec.market.service;

import com.cibertec.market.dto.ChargeRequestDTO;
import com.cibertec.market.dto.ChargeResponseDTO;
import com.cibertec.market.model.Charge;
import com.cibertec.market.repository.ChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChargeService {
    private final ChargeRepository chargeRepository;
    private final ObjectMapper objectMapper;

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
                .amount(chargeRequestDTO.getAmount())
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
                .amount(chargeRequestDTO.getAmount())
                .build();
        return convertToResponseDTO(chargeRepository.save(charge));
    }

    public void delete(Long id) {
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
                .amount(charge.getAmount())
                .build();
    }

}
