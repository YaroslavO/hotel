package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.HqlQueryHotelRoomSearchBuilder;
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

    private SessionFactory sessionFactory;

    public HotelRoomDaoImpl() {
    }

    @Autowired
    public HotelRoomDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public HotelRoom getHotelRoomById(Integer id) {
        if (id == null) {
            throw new NullPointerException();
        }

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
    public List<HotelRoom> searchHotelRoomByParameter(HqlQueryHotelRoomSearchBuilder hqlBuilder) {

        Query allQuery = sessionFactory.getCurrentSession()
                .createQuery(hqlBuilder.generateQuery());

        allQuery.setParameter("startDate", new Date(hqlBuilder.getStartTime()));
        allQuery.setParameter("endDate", new Date(hqlBuilder.getEndTime()));

        setSafeQueryParameter(allQuery, "classRoom", hqlBuilder.getBudgetRoomType());
        setSafeQueryParameter(allQuery, "sizeRoom", hqlBuilder.getSizeRoomType());

        if (hqlBuilder.getCountHotelRoom() == null) {
            allQuery.setMaxResults(20);
        } else {
            allQuery.setMaxResults(hqlBuilder.getCountHotelRoom());
        }

        return allQuery.list();
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<HotelRoom> getMaidRoomsOnDay(java.util.Date thisDay) {
        String stringQuery = "select hr from HotelRoom hr, Maid m, Reservation r where " +
                "hr.id = r.hotelRoom and " +
                "m.date <> :date and " +
                "m.reservation = r.id and " +
                ":date between r.startDate and r.endDate ";

        Query query = sessionFactory
                .getCurrentSession()
                .createQuery(stringQuery)
                .setParameter("date", thisDay);

        return query.list();
    }

    private void setSafeQueryParameter(Query q, String paramName, Object paramValue) {
        if (paramValue == null) return;

        q.setParameter(paramName, paramValue);
    }
}
