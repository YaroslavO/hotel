package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Period;
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
    public Reservation save(Reservation reservation) {
        sessionFactory.getCurrentSession().save(reservation);
        return reservation;
    }

    @Override
    public boolean isGoodPeriodForThisRoom(HotelRoom room, Period period) {
        return false;
    }
}
