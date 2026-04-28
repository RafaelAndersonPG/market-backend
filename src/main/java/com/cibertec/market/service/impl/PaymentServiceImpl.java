package com.cibertec.market.service.impl;

import com.cibertec.market.model.Payment;
import com.cibertec.market.repository.PaymentRepository;
import com.cibertec.market.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository repository;

    @Override
    public List<Payment> getAll() {
        return repository.findAll();
    }

    @Override
    public Payment getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pago no encontrado"));
    }

    @Override
    public Payment save(Payment payment) {

        if (payment.getAmount().doubleValue() <= 0) {
            throw new RuntimeException("Monto inválido");
        }

        if (payment.getStall() == null || payment.getStall().getOwner() == null) {
            throw new RuntimeException("El puesto no tiene dueño");
        }

        return repository.save(payment);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}