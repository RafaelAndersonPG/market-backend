package com.cibertec.market.controller;

import com.cibertec.market.dto.ChargeRequestDTO;
import com.cibertec.market.dto.ChargeResponseDTO;
import com.cibertec.market.service.ChargeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cargo")
@RequiredArgsConstructor
public class ChargeController {
    private final ChargeService chargeService;

    @GetMapping
    public ResponseEntity<List<ChargeResponseDTO>> read() {
        return ResponseEntity.ok(chargeService.getAll());
    }

    @PostMapping
    public ResponseEntity<ChargeResponseDTO> create(@RequestBody @Valid ChargeRequestDTO chargeRequestDTO) {
        return ResponseEntity.status(201).body(chargeService.save(chargeRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ChargeResponseDTO> edit(@PathVariable Long id, @RequestBody @Valid ChargeRequestDTO chargeRequestDTO) {
        if (!chargeService.existID(id)) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.status(201).body(chargeService.update(id, chargeRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!chargeService.existID(id)) {
            return ResponseEntity.notFound().build();
        }
        chargeService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
