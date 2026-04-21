package com.cibertec.market.service;

import com.cibertec.market.dto.TransferRequestDTO;
import com.cibertec.market.dto.TransferResponseDTO;
import com.cibertec.market.model.Owner;
import com.cibertec.market.model.Stall;
import com.cibertec.market.model.Transfer;
import com.cibertec.market.repository.OwnerRepository;
import com.cibertec.market.repository.StallRepository;
import com.cibertec.market.repository.TransferRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferService {
    private final TransferRepository transferRepository;
    private final StallRepository stallRepository;
    private final OwnerRepository ownerRepository;

    public List<TransferResponseDTO> getAll() {
        return transferRepository.findAllByOrderByStallNameAsc()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional
    public String save(TransferRequestDTO transferRequestDTO) {
        Stall stall = stallRepository.findById(transferRequestDTO.getStallId())
                .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
        Owner newOwner = ownerRepository.findByDni(transferRequestDTO.getOwnerDni())
                .orElseGet(() -> {
                    Owner owner = Owner.builder()
                            .name(transferRequestDTO.getOwnerName())
                            .dni(transferRequestDTO.getOwnerDni())
                            .build();
                    return ownerRepository.save(owner);
                });
        Transfer transfer = Transfer.builder()
                .stall(stall)
                .amount(transferRequestDTO.getAmount())
                .date(LocalDate.now())
                .build();
        transferRepository.save(transfer);
        stall.setOwner(newOwner);
        stallRepository.save(stall);
        return "Traspaso del puesto " + stall.getName() + " realizado con éxito a " + newOwner.getName();
    }

    private TransferResponseDTO convertToResponseDTO(Transfer transfer) {
        return TransferResponseDTO.builder()
                .id(transfer.getId())
                .amount(transfer.getAmount())
                .date(transfer.getDate())
                .stallId(transfer.getStall() != null ? transfer.getStall().getId() : null)
                .ownerDni(transfer.getStall() != null && transfer.getStall().getOwner() != null
                        ? transfer.getStall().getOwner().getDni() : null)
                .build();
    }

}
