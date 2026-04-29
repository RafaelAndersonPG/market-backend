package com.cibertec.market.service;

import com.cibertec.market.dto.ChargeRequestDTO;
import com.cibertec.market.dto.ChargeResponseDTO;

import java.util.List;

public interface ChargeService {
    List<ChargeResponseDTO> getAll();

    ChargeResponseDTO save(ChargeRequestDTO chargeRequestDTO);

    ChargeResponseDTO update(Long id, ChargeRequestDTO chargeRequestDTO);

    void delete(Long id);

    boolean existID(Long id);
}
