package com.cibertec.market.service.impl;

import com.cibertec.market.dto.StallRequestDTO;
import com.cibertec.market.dto.StallResponseDTO;
import com.cibertec.market.model.Owner;
import com.cibertec.market.model.Stall;
import com.cibertec.market.repository.OwnerRepository;
import com.cibertec.market.repository.StallRepository;
import com.cibertec.market.service.StallService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tools.jackson.databind.ObjectMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StallServiceImpl implements StallService {
    private final StallRepository stallRepository;
    private final OwnerRepository ownerRepository;
    private final ObjectMapper objectMapper;
    private static final Long MARKET_ID = 1L;

    public List<StallResponseDTO> getAll() {
        return stallRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    public StallResponseDTO save(StallRequestDTO stallRequestDTO) {
        if (stallRepository.existsByName(stallRequestDTO.getName())) {
            throw new RuntimeException("El puesto ya existe");
        }
        Owner market = ownerRepository.findById(MARKET_ID)
                .orElseThrow(() -> new RuntimeException("Error crítico: La entidad Mercado (ID 1) no existe en la base de datos"));
        Stall stall = Stall.builder()
                .name(stallRequestDTO.getName())
                .owner(market)
                .build();
        return convertToResponseDTO(stallRepository.save(stall));
    }

    public StallResponseDTO update(Long id, StallRequestDTO stallRequestDTO) {
        Stall stall = stallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
        if (stallRepository.existsByNameAndIdNot(stallRequestDTO.getName(), id)) {
            throw new RuntimeException("El número de puesto ya está siendo usado");
        }
        stall.setName(stallRequestDTO.getName());
        return convertToResponseDTO(stallRepository.save(stall));
    }

    public void delete(Long id) {
        Stall stall = stallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
        stallRepository.deleteById(id);
    }

    public boolean existID(Long id) {
        return stallRepository.existsById(id);
    }

    private StallResponseDTO convertToResponseDTO(Stall stall) {
        return StallResponseDTO.builder()
                .id(stall.getId())
                .name(stall.getName())
                .ownerName(stall.getOwner().getName())
                .build();
    }
}
