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
        return ResponseEntity.status(201).body(stallService.save(stallRequestDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> edit(@PathVariable Long id, @RequestBody @Valid StallRequestDTO stallRequestDTO) {
        return ResponseEntity.status(201).body(stallService.update(id, stallRequestDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stallService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
