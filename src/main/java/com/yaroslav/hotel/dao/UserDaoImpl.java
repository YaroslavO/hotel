package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.User;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by PC on 21.06.2015.
 */

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User getUserByLogin(String login) {
        User user = (User) sessionFactory
                .getCurrentSession()
                .createCriteria(User.class)
                .add(Restrictions.eq("login", login))
                .uniqueResult();

        return user;
    }

    @Override
    public void save(User user) {
        sessionFactory
                .getCurrentSession()
                .save(user);
    }
}
