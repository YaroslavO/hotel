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
    public HotelRoom getHotelRoomById(Integer id) {
        return (HotelRoom) sessionFactory
                .getCurrentSession()
                .get(HotelRoom.class, id);
    }

    @Override
    public HotelRoom saveHotelRoom(HotelRoom hotelRoom) {
        sessionFactory.getCurrentSession().save(hotelRoom);
        return hotelRoom;
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

        String query = "select HR from HotelRoom HR where ";

        if (parameter.budgetRoomType != null) {
            if (parameter.thisBudgetRoomType) {
                query += "HR.classRoom = :classRoom  and ";
            } else {
                query += "HR.classRoom <> :classRoom  and ";
            }
        }

        if (parameter.sizeRoomType != null) {
            query += "HR.type = :sizeRoom and ";
        }

        query += "( not exists " +
                "(from Reservation R where R.hotelRoom = HR and " +
                "(R.startDate between :startDate and :endDate or " +
                "R.endDate between :startDate and :endDate)))";

        Query allQuery = sessionFactory.getCurrentSession()
                .createQuery(query);

        Date endDate = new Date(parameter.period.end.getTime());
        Date beginDate = new Date(parameter.period.begin.getTime());
        allQuery.setParameter("startDate", beginDate);
        allQuery.setParameter("endDate", endDate);

        if (parameter.budgetRoomType != null) {
            allQuery.setParameter("classRoom", parameter.budgetRoomType);
        }

        if (parameter.sizeRoomType != null) {
            allQuery.setParameter("sizeRoom", parameter.sizeRoomType);
        }

        if (parameter.countHotelRoom == null) {
            allQuery.setMaxResults(20);
        } else {
            allQuery.setMaxResults(parameter.countHotelRoom);
        }

        return allQuery.list();
    }
}
