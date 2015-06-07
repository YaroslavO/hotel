package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
    public void updateHotelRoom(HotelRoom hotelRoom) {
        sessionFactory.getCurrentSession().update(hotelRoom);
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
}
