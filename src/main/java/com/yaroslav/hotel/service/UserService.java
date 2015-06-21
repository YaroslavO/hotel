package com.yaroslav.hotel.service;

import com.yaroslav.hotel.entity.User;

/**
 * Created by PC on 21.06.2015.
 */
public interface UserService {
    User getUserByLogin(String login);
}
