package com.cibertec.market.service.impl;

import com.cibertec.market.model.AccountSequence;
import com.cibertec.market.repository.AccountSequenceRepository;
import com.cibertec.market.service.AccountSequenceService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountSequenceServiceImpl implements AccountSequenceService {

    private final AccountSequenceRepository repository;

    @Transactional
    public synchronized Long getNextNumber() {

        AccountSequence seq = repository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Secuencia no inicializada"));

        Long current = seq.getValue();
        seq.setValue(current + 1);

        repository.save(seq);

        return current;
    }
}
