package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.UserDao;
import com.yaroslav.hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by PC on 21.06.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }
}
