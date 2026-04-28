package com.cibertec.market.service;

import com.cibertec.market.dto.TransferRequestDTO;
import com.cibertec.market.dto.TransferResponseDTO;

import java.util.List;

public interface TransferService {

    List<TransferResponseDTO> getAll();

    String save(TransferRequestDTO request);
}