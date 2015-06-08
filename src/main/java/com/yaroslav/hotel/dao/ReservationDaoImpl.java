package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.Reservation;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by employee on 6/8/15.
 */

@Repository
public class ReservationDaoImpl implements ReservationDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Reservation reservation) {
        sessionFactory.getCurrentSession().save(reservation);
    }
}
