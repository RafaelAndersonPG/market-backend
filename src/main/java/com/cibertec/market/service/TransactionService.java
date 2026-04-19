package com.cibertec.market.service;

import com.cibertec.market.dto.TransactionRequestDTO;
import com.cibertec.market.dto.TransactionResponseDTO;
import com.cibertec.market.model.Owner;
import com.cibertec.market.model.Stall;
import com.cibertec.market.model.Transaction;
import com.cibertec.market.repository.OwnerRepository;
import com.cibertec.market.repository.StallRepository;
import com.cibertec.market.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final StallRepository stallRepository;
    private final OwnerRepository ownerRepository;

    public List<TransactionResponseDTO> getAll() {
        return transactionRepository.findAllByOrderByStallNameAsc()
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    @Transactional
    public String save(TransactionRequestDTO transactionRepositoryDTO) {
        Stall stall = stallRepository.findById(transactionRepositoryDTO.getStallId())
                .orElseThrow(() -> new RuntimeException("Puesto no encontrado"));
        Owner newOwner = ownerRepository.findByDni(transactionRepositoryDTO.getOwnerDni())
                .orElseGet(() -> {
                    Owner owner = Owner.builder()
                            .name(transactionRepositoryDTO.getOwnerName())
                            .dni(transactionRepositoryDTO.getOwnerDni())
                            .build();
                    return ownerRepository.save(owner);
                });
        Transaction transaction = Transaction.builder()
                .stall(stall)
                .amount(transactionRepositoryDTO.getAmount())
                .date(LocalDate.now())
                .build();
        transactionRepository.save(transaction);
        stall.setOwner(newOwner);
        stallRepository.save(stall);
        return "Traspaso del puesto " + stall.getName() + " realizado con éxito a " + newOwner.getName();
    }

    private TransactionResponseDTO convertToResponseDTO(Transaction transaction) {
        return TransactionResponseDTO.builder()
                .id(transaction.getId())
                .amount(transaction.getAmount())
                .date(transaction.getDate())
                .stallId(transaction.getStall() != null ? transaction.getStall().getId() : null)
                .ownerDni(transaction.getStall() != null && transaction.getStall().getOwner() != null
                        ? transaction.getStall().getOwner().getDni() : null)
                .build();
    }

}
