package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.User;
import org.hibernate.SessionFactory;
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
        return (User) sessionFactory
                .getCurrentSession()
                .createQuery("from User u where u.login = :login")
                .setParameter("login", login)
                .uniqueResult();
    }
}
