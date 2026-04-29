package com.cibertec.market.service.impl;

import com.cibertec.market.dto.PaymentRequestDTO;
import com.cibertec.market.dto.PaymentResponseDTO;
import com.cibertec.market.dto.TransactionRequestDTO;
import com.cibertec.market.enums.TransactionType;
import com.cibertec.market.exception.BusinessException;
import com.cibertec.market.exception.ResourceNotFoundException;
import com.cibertec.market.model.Debt;
import com.cibertec.market.model.Payment;
import com.cibertec.market.repository.DebtRepository;
import com.cibertec.market.repository.PaymentRepository;
import com.cibertec.market.service.PaymentService;
import com.cibertec.market.service.TransactionService;
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
    private final TransactionService transactionService;

    @Override
    @Transactional
    public PaymentResponseDTO registerPayment(PaymentRequestDTO request) {

        Debt debt = findDebtOrThrow(request.getDebtId());

        validateDebtNotPaid(debt);

        debt.setPaid(true);

        Payment payment = buildPayment(debt);
        Payment savedPayment = paymentRepository.save(payment);

        registerExpenseTransaction(request, debt);

        return convertToResponseDTO(savedPayment);
    }

    @Override
    @Transactional
    public List<PaymentResponseDTO> payAllDebtsByStall(Long stallId) {

        List<Debt> debts = debtRepository.findByStallIdOrderByYearDescMonthDesc(stallId);

        if (debts.isEmpty()) {
            throw new BusinessException("El puesto no tiene deudas pendientes.");
        }

        List<Payment> payments = debts.stream()
                .map(this::payDebt)
                .toList();

        return paymentRepository.saveAll(payments)
                .stream()
                .map(this::convertToResponseDTO)
                .toList();
    }

    private Debt findDebtOrThrow(Long id) {
        return debtRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Deuda no encontrada"));
    }

    private void validateDebtNotPaid(Debt debt) {
        if (Boolean.TRUE.equals(debt.getPaid())) {
            throw new BusinessException("Esta deuda ya ha sido cancelada anteriormente.");
        }
    }

    private Payment payDebt(Debt debt) {
        debt.setPaid(true);
        return buildPayment(debt);
    }

    private Payment buildPayment(Debt debt) {
        return Payment.builder()
                .paymentDate(LocalDateTime.now())
                .amountPaid(debt.getAmount())
                .debt(debt)
                .build();
    }

    private void registerExpenseTransaction(PaymentRequestDTO request, Debt debt) {
        TransactionRequestDTO tx = TransactionRequestDTO.builder()
                .accountId(request.getAccountId())
                .amount(debt.getAmount())
                .type(TransactionType.EXPENSE)
                .description(request.getDescription())
                .build();

        transactionService.registerTransaction(tx);
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
