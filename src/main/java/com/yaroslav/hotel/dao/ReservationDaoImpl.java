package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Period;
import com.yaroslav.hotel.entity.Reservation;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;

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
    public boolean canBeReserved(HotelRoom room, Period period) {
        String query = "select R from Reservation R";
//        where R.hotelRoom = :room and " +
//                ":startDate between R.startDate and R.endDate " +
//                "and :endDate between R.startDate and R.endDate";

        Query hibernateQuery = sessionFactory.getCurrentSession()
                .createQuery(query);
//        hibernateQuery.setParameter("startDate", new Date(period.begin.getTime()));
//        hibernateQuery.setParameter("endDate", new Date(period.end.getTime()));
//        hibernateQuery.setParameter("room", room);

        return hibernateQuery.list().size() == 0;
    }
}
