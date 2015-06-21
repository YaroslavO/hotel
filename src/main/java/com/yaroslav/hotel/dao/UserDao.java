package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.User;

/**
 * Created by PC on 21.06.2015.
 */
public interface UserDao {
    User getUserByLogin(String login);
}
