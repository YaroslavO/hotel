package com.yaroslav.hotel.service;

import com.yaroslav.hotel.config.DatabaseConfigTest;
import com.yaroslav.hotel.dao.UserDao;
import com.yaroslav.hotel.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.verify;

/**
 * Created by employee on 6/22/15.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({
        @ContextConfiguration(classes = {DatabaseConfigTest.class}),
        @ContextConfiguration(locations = {"classpath:UserServiceImplTest-context.xml"})
})
public class UserServiceImplTest {

    @Qualifier("userServiceImpl")
    @Autowired
    UserService userService;

    @Qualifier("userDaoImpl")
    @Autowired
    UserDao userDao;

    @Test
    public void saveUser() throws Exception {
        User user = new User();

        userService.save(user);

        verify(userDao).save(user);
    }
}