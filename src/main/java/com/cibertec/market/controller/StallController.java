package com.cibertec.market.controller;

import com.cibertec.market.dto.StallRequestDTO;
import com.cibertec.market.dto.StallResponseDTO;
import com.cibertec.market.service.StallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/puesto")
public class StallController {
    private final StallService stallService;

    @GetMapping
    public ResponseEntity<List<StallResponseDTO>> read() {
        return ResponseEntity.ok(stallService.getAll());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid StallRequestDTO stallRequestDTO) {
        try {
            return ResponseEntity.status(201).body(stallService.save(stallRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody @Valid StallRequestDTO stallRequestDTO) {
        if (!stallService.existID(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            return ResponseEntity.status(201).body(stallService.update(id, stallRequestDTO));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!stallService.existID(id)) {
            return ResponseEntity.notFound().build();
        }
        stallService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
