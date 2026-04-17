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
                .map(a -> objectMapper.convertValue(a, ChargeResponseDTO.class))
                .toList();
    }

    public ChargeResponseDTO save(ChargeRequestDTO chargeRequestDTO) {
        if (chargeRepository.existsByName(chargeRequestDTO.getName())) {
            throw new RuntimeException("El nombre fue registrado anteriormente");
        }
        var charge = objectMapper.convertValue(chargeRequestDTO, Charge.class);
        return objectMapper.convertValue(chargeRepository.save(charge), ChargeResponseDTO.class);
    }

    public ChargeResponseDTO update(Long id, ChargeRequestDTO chargeRequestDTO) {
        if (chargeRepository.existsByNameAndIdNot(chargeRequestDTO.getName(), id)) {
            throw new RuntimeException("El nombre ya está registrado");
        }
        var charge = objectMapper.convertValue(chargeRequestDTO, Charge.class);
        charge.setId(id);
        return objectMapper.convertValue(chargeRepository.save(charge), ChargeResponseDTO.class);
    }

    public void delete(Long id) {
        chargeRepository.deleteById(id);
    }

    public boolean existID(Long id) {
        return chargeRepository.existsById(id);
    }

}
