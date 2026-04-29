package com.cibertec.market.service;

import com.cibertec.market.dto.PaymentRequestDTO;
import com.cibertec.market.dto.PaymentResponseDTO;

import java.util.List;

public interface PaymentService {

    PaymentResponseDTO registerPayment(PaymentRequestDTO paymentRequestDTO);

    List<PaymentResponseDTO> payAllDebtsByStall(Long stallId);
}
