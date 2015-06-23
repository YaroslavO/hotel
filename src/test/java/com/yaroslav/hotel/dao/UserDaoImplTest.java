package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.config.AbstractDataBaseTest;
import com.yaroslav.hotel.entity.User;
import org.hibernate.PropertyValueException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * Created by employee on 6/23/15.
 */

public class UserDaoImplTest extends AbstractDataBaseTest{

    @Autowired
    private UserDao userDao;

    @Test(expected = PropertyValueException.class)
    public void saveEmptyUser() throws Exception {
        //given
        User user = new User();

        //when
        userDao.save(user);

        //then
        assertNull(user.getId());
        assertNull(user.getLogin());
        assertNull(user.getPassword());
        assertNull(user.getRoles());
    }
}