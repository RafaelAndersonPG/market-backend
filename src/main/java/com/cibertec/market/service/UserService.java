package com.cibertec.market.service;

import com.cibertec.market.model.User;

public interface UserService {

    User login(String username, String password);
}
