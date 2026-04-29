package com.cibertec.market.service;

import com.cibertec.market.dto.ChargeRequestDTO;
import com.cibertec.market.dto.ChargeResponseDTO;
import com.cibertec.market.model.Charge;
import com.cibertec.market.repository.ChargeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

public interface ChargeService {
    List<ChargeResponseDTO> getAll();

    ChargeResponseDTO save(ChargeRequestDTO chargeRequestDTO);

    ChargeResponseDTO update(Long id, ChargeRequestDTO chargeRequestDTO);

    void delete(Long id);

    boolean existID(Long id);
}
