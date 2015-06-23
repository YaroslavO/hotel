package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.Role;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by employee on 6/23/15.
 */

@Repository
public class RoleDaoImpl implements RoleDao{

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role getRoleByName(String name) {
        return (Role) sessionFactory
                .getCurrentSession()
                .createCriteria(Role.class)
                .add(Restrictions.eq("name", name))
                .uniqueResult();
    }
}
