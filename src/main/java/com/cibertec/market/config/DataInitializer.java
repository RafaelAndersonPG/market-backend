package com.cibertec.market.config;

import com.cibertec.market.model.AccountSequence;
import com.cibertec.market.repository.AccountSequenceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AccountSequenceRepository repository;

    @EventListener(ApplicationReadyEvent.class)
    public void init() {

        if (!repository.existsById(1L)) {
            AccountSequence seq = new AccountSequence();
            seq.setId(1L);
            seq.setValue(100000000000L);
            repository.save(seq);
        }
    }
}
