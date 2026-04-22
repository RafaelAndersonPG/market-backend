package com.cibertec.market.service.impl;

import com.cibertec.market.model.User;
import com.cibertec.market.repository.UserRepository;
import com.cibertec.market.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public User login(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
}
