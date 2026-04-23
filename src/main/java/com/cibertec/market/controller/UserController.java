package com.cibertec.market.controller;

import com.cibertec.market.dto.AuthResponseDto;
import com.cibertec.market.dto.LoginDTO;
import com.cibertec.market.model.User;
import com.cibertec.market.security.JWTAuthenticationConfig;
import com.cibertec.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UserController {

    private final JWTAuthenticationConfig jwtAuthenticationConfig;
    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<Object>> getUsuarios() {
        List<Object> list = new ArrayList<>();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/unprotected")
    public ResponseEntity<List<Object>> getUnprotected() {
        List<Object> list = new ArrayList<>();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDTO loginDTO) {

        User user = userService.login(loginDTO.getUsername(), loginDTO.getPassword());

        if (user == null) {
            throw new UsernameNotFoundException("Credenciales inválidas");
        }

        String token = jwtAuthenticationConfig.getJWTToken(
                user.getUsername(),
                user.getName(),
                user.getLastname()
        );

        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
