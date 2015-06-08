package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;
import com.yaroslav.hotel.entity.Reservation;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * Created by employee on 6/5/15.
 */
@Repository
public class HotelRoomDaoImpl implements HotelRoomDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addHotelRoom(HotelRoom hotelRoom) {
        sessionFactory.getCurrentSession().save(hotelRoom);
    }

    @Override
    public HotelRoom updateHotelRoom(HotelRoom hotelRoom) {
        sessionFactory.getCurrentSession().update(hotelRoom);
        return hotelRoom;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HotelRoom> getAllHotelRoom() {
        return sessionFactory
                .getCurrentSession()
                .createCriteria(HotelRoom.class)
                .list();
    }

    @Override
    public void deleteHotelRoom(HotelRoom room) {
        sessionFactory
                .getCurrentSession()
                .delete(room);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HotelRoom> searchHotelRoomByParameter(Parameter parameter) {
        // make with criteria
        String sqlQuery = "select hr.* from room_to_reservation as rtr " +
                "JOIN HOTEL_ROOM AS hr on rtr.fk_room = hr.ID " +
                "JOIN reservations AS r ON rtr.fk_reservation = r.id " +
                "where NOT EXISTS (select * from reservations r2 " +
                "where r.id = r2.id AND " +
                "r.date between :begin_date and :end_date)";

        Date endDate = new Date(parameter.period.end.getTime());
        Date beginDate = new Date(parameter.period.begin.getTime());

        Query allQuery = sessionFactory
                .getCurrentSession()
                .createSQLQuery(sqlQuery)
                .addEntity(HotelRoom.class)
                .setParameter("begin_date", beginDate)
                .setParameter("end_date", endDate);

        if (parameter.countHotelRoom == null) {
            allQuery.setMaxResults(20);
        } else {
            allQuery.setMaxResults(parameter.countHotelRoom);
        }

        return allQuery.list();
    }
}
