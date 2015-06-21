package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
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
        SQLQuery query = sessionFactory
                .getCurrentSession()
                .createSQLQuery("SELECT * from \"USER\" u where u.login = :login");
        query.addEntity(User.class);
        query.setParameter("login", login)
                .setMaxResults(1);
        User user = (User) query
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
