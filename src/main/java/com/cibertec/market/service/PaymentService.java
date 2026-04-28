package com.cibertec.market.service;

import com.cibertec.market.model.Payment;

import java.util.List;

public interface PaymentService {

    List<Payment> getAll();

    Payment getById(Long id);

    Payment save(Payment payment);

    void delete(Long id);
}