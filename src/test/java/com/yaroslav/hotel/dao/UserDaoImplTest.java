package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.config.AbstractDataBaseTest;
import com.yaroslav.hotel.entity.User;
import org.hibernate.PropertyValueException;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.*;

/**
 * Created by employee on 6/23/15.
 */

public class UserDaoImplTest extends AbstractDataBaseTest{

    @Autowired
    private UserDao userDao;

    @Autowired
    private SessionFactory sessionFactory;

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

    @Test
    public void canGetUser__byLogin_ifNotExist() throws Exception {
        User user = userDao.getUserByLogin("root");

        assertNull(user);
    }

    @Test(expected = ConstraintViolationException.class)
    public void canCreate__user_ifLoginExist() throws Exception {
        // given
        User user1 = new User("root", "root");
        User user2 = new User("root", "root");

        // when
        userDao.save(user1);
        userDao.save(user2);

        // then
        assertNotNull(user1.getId());
        assertNull(user2.getId());
    }

    @Test
    public void canGet_user_ifExist() throws Exception {
        //given
        User user = new User("root", "root");

        //when
        userDao.save(user);
        sessionFactory.getCurrentSession().flush();
        user = userDao.getUserByLogin("root");

        //then
        assertThat(user.getId(), is(1));
        assertThat(user.getLogin(), not(""));
        assertThat(user.getLogin(), is("root"));
        assertThat(user.getPassword(), not(""));
        assertThat(user.getPassword(), is("root"));
    }
}