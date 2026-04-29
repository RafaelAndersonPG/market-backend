package com.cibertec.market.service.impl;

import com.cibertec.market.model.User;
import com.cibertec.market.repository.UserRepository;
import com.cibertec.market.security.JWTAuthenticationConfig;
import com.cibertec.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JWTAuthenticationConfig jwtAuthenticationConfig;

    public String login(String username, String rawPassword) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Usuario no existe");
        }

        if (!passwordEncoder.matches(rawPassword, user.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        return jwtAuthenticationConfig.getJWTToken(
                user.getUsername(),
                user.getName(),
                user.getLastname()
        );
    }
}
