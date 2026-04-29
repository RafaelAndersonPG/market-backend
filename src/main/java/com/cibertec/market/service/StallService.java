package com.cibertec.market.service;

import com.cibertec.market.dto.StallRequestDTO;
import com.cibertec.market.dto.StallResponseDTO;

import java.util.List;

public interface StallService {

    List<StallResponseDTO> getAll();
    StallResponseDTO save(StallRequestDTO stallRequestDTO);
    StallResponseDTO update(Long id, StallRequestDTO stallRequestDTO);
    void delete(Long id);
}
