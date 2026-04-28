package com.cibertec.market.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig {

    @Autowired
    private JWTAuthorizationFilter jwtAuthorizationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(HttpMethod.GET, "/usuarios/unprotected").permitAll()
                                .requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                                .requestMatchers("/cargo/**").permitAll()
                                .requestMatchers("/deuda/**").permitAll()
                                .requestMatchers("/pago/**").permitAll()
                                .requestMatchers("/puesto/**").permitAll()
                                .requestMatchers("/traspaso/**").permitAll()
                                .anyRequest()
                                .authenticated())
                .addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
