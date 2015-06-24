package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.RoleDao;
import com.yaroslav.hotel.dao.UserDao;
import com.yaroslav.hotel.entity.Role;
import com.yaroslav.hotel.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by PC on 21.06.2015.
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Override
    public User getUserByLogin(String login) {
        return userDao.getUserByLogin(login);
    }

    @Override
    public void save(User user, String role) {
        role = role.toUpperCase();

        Role roleEntity = roleDao.getRoleByName(role);
        List<Role> roles = new ArrayList<>();
        roles.add(roleEntity);

        user.setRoles(roles);
        userDao.save(user);
    }
}
