package com.cibertec.market.service.impl;

import com.cibertec.market.dto.TransferRequestDTO;
import com.cibertec.market.dto.TransferResponseDTO;
import com.cibertec.market.repository.TransferRepository;
import com.cibertec.market.service.TransferService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

    private final TransferRepository transferRepository;

    @Override
    public List<TransferResponseDTO> getAll() {
        return new ArrayList<>();
    }

    @Override
    public String save(TransferRequestDTO request) {
        return "Transferencia registrada";
    }
}