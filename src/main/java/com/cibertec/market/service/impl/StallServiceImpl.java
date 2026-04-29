package com.cibertec.market.service.impl;

import com.cibertec.market.dto.StallRequestDTO;
import com.cibertec.market.dto.StallResponseDTO;
import com.cibertec.market.exception.ResourceNotFoundException;
import com.cibertec.market.model.Owner;
import com.cibertec.market.model.Stall;
import com.cibertec.market.repository.OwnerRepository;
import com.cibertec.market.repository.StallRepository;
import com.cibertec.market.service.AccountSequenceService;
import com.cibertec.market.service.AccountService;
import com.cibertec.market.service.StallService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StallServiceImpl implements StallService {
    private final StallRepository stallRepository;
    private final OwnerRepository ownerRepository;
    private final AccountService accountService;
    private final AccountSequenceService sequenceService;
    private static final Long MARKET_ID = 1L;

    public List<StallResponseDTO> getAll() {
        return stallRepository.findAll()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional
    public StallResponseDTO save(StallRequestDTO stallRequestDTO) {

        if (stallRepository.existsByName(stallRequestDTO.getName())) {
            throw new RuntimeException("El puesto ya existe");
        }

        Owner market = ownerRepository.findById(MARKET_ID)
                .orElseThrow(() -> new RuntimeException("Mercado no existe"));

        Stall stall = Stall.builder()
                .name(stallRequestDTO.getName())
                .owner(market)
                .build();

        Stall savedStall = stallRepository.save(stall);

        Long accountNumber = sequenceService.getNextNumber();

        accountService.createAccountForStall(savedStall, accountNumber);

        return convertToResponseDTO(savedStall);
    }

    public StallResponseDTO update(Long id, StallRequestDTO stallRequestDTO) {

        if(notExistById(id)) {
            throw new ResourceNotFoundException("No existe el puesto");
        }

        Stall stall = stallRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
        if (stallRepository.existsByNameAndIdNot(stallRequestDTO.getName(), id)) {
            throw new RuntimeException("El número de puesto ya está siendo usado");
        }
        stall.setName(stallRequestDTO.getName());
        return convertToResponseDTO(stallRepository.save(stall));
    }

    @Transactional
    public void delete(Long id) {
        Stall stall = stallRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Puesto no encontrado"));

        accountService.deleteByStallId(id);
        stallRepository.delete(stall);
    }

    public boolean notExistById(Long id) {
        return !stallRepository.existsById(id);
    }

    private StallResponseDTO convertToResponseDTO(Stall stall) {
        return StallResponseDTO.builder()
                .id(stall.getId())
                .name(stall.getName())
                .ownerName(stall.getOwner().getName())
                .build();
    }
}
