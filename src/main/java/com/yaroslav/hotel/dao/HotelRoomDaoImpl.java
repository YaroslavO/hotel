package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.sql.Date;
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
        String query = "select HR from HotelRoom HR, Reservation R where HR.id = R.hotelRoom and " +
                " not exists (from Reservation R2 where R2.id = R.id and R2.startDate < :startDate and " +
                "R2.endDate > :endDate )";

        Query allQuery = sessionFactory.getCurrentSession()
                .createQuery(query);

        Date endDate = new Date(parameter.period.end.getTime());
        Date beginDate = new Date(parameter.period.begin.getTime());

        allQuery.setParameter("startDate", beginDate);
        allQuery.setParameter("endDate", endDate);

        if (parameter.countHotelRoom == null) {
            allQuery.setMaxResults(20);
        } else {
            allQuery.setMaxResults(parameter.countHotelRoom);
        }

        return allQuery.list();
    }
}
