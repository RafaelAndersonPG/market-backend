package com.cibertec.market.controller;

import com.cibertec.market.dto.PaymentRequestDTO;
import com.cibertec.market.dto.PaymentResponseDTO;
import com.cibertec.market.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/pago")
@RequiredArgsConstructor
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping("/por-deuda")
    public ResponseEntity<PaymentResponseDTO> create(@Valid @RequestBody PaymentRequestDTO dto) {
        return ResponseEntity.ok(paymentService.registerPayment(dto));
    }

    @PostMapping("/por-puesto/{stallId}")
    public ResponseEntity<List<PaymentResponseDTO>> payAll(@PathVariable Long stallId) {
        return ResponseEntity.ok(paymentService.payAllDebtsByStall(stallId));
    }
}
