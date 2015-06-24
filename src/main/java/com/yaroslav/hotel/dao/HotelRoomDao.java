package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.HqlQueryHotelRoomSearchBuilder;

import java.util.Date;
import java.util.List;

/**
 * Created by employee on 6/5/15.
 */

public interface HotelRoomDao {
    HotelRoom getHotelRoomById(Integer id);

    HotelRoom saveHotelRoom(HotelRoom hotelRoom);

    HotelRoom updateHotelRoom(HotelRoom hotelRoom);

    List<HotelRoom> getAllHotelRoom();

    void deleteHotelRoom(HotelRoom room);

    List<HotelRoom> searchHotelRoomByParameter(HqlQueryHotelRoomSearchBuilder hqlQueryHotelRoomSearchBuilder);

    List getMaidRoomsOnDay(Date thisDay);
}
