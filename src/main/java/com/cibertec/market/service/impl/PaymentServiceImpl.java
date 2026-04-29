package com.cibertec.market.service.impl;

import com.cibertec.market.dto.PaymentRequestDTO;
import com.cibertec.market.dto.PaymentResponseDTO;
import com.cibertec.market.model.Debt;
import com.cibertec.market.model.Payment;
import com.cibertec.market.repository.DebtRepository;
import com.cibertec.market.repository.PaymentRepository;
import com.cibertec.market.service.PaymentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;
    private final DebtRepository debtRepository;

    @Transactional
    public PaymentResponseDTO registerPayment(PaymentRequestDTO paymentRequestDTO) {
        Debt debt = debtRepository.findById(paymentRequestDTO.getDebtId())
                .orElseThrow(() -> new RuntimeException("Deuda no encontrada"));
        if (debt.getPaid()) {
            throw new RuntimeException("Esta deuda ya ha sido cancelada anteriormente.");
        }
        Payment payment = Payment.builder()
                .paymentDate(LocalDateTime.now())
                .amountPaid(debt.getAmount())
                .debt(debt)
                .build();
        debt.setPaid(true);
        debtRepository.save(debt);
        return convertToResponseDTO(paymentRepository.save(payment));
    }

    @Transactional
    public List<PaymentResponseDTO> payAllDebtsByStall(Long stallId) {
        List<Debt> pendingDebts = debtRepository.findByStallIdAndPaidFalse(stallId);
        if (pendingDebts.isEmpty()) {
            throw new RuntimeException("El puesto no tiene deudas pendientes.");
        }
        List<Payment> payments = pendingDebts.stream().map(debt -> {
            debt.setPaid(true);
            return Payment.builder()
                    .paymentDate(LocalDateTime.now())
                    .amountPaid(debt.getAmount())
                    .debt(debt)
                    .build();
        }).toList();
        return paymentRepository.saveAll(payments).stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    private PaymentResponseDTO convertToResponseDTO(Payment payment) {
        return PaymentResponseDTO.builder()
                .paymentId(payment.getId())
                .stallName(payment.getDebt().getStall().getName())
                .chargeName(payment.getDebt().getCharge().getName())
                .amount(payment.getAmountPaid())
                .date(payment.getPaymentDate())
                .build();
    }
}
