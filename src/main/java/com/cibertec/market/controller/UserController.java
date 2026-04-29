package com.cibertec.market.controller;

import com.cibertec.market.dto.AuthResponseDto;
import com.cibertec.market.dto.LoginDTO;
import com.cibertec.market.security.JWTAuthenticationConfig;
import com.cibertec.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/usuarios")
public class UserController {

    private final JWTAuthenticationConfig jwtAuthenticationConfig;
    private final UserService userService;



    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody LoginDTO loginDTO) {

        String token = userService.login(loginDTO.getUsername(), loginDTO.getPassword());

        return ResponseEntity.ok(new AuthResponseDto(token));
    }
}
