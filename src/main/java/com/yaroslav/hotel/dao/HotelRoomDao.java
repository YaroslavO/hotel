package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;

import java.util.List;

/**
 * Created by employee on 6/5/15.
 */

public interface HotelRoomDao {
    HotelRoom getHotelRoomById(Integer id);
    void saveHotelRoom(HotelRoom hotelRoom);
    HotelRoom updateHotelRoom(HotelRoom hotelRoom);
    List<HotelRoom> getAllHotelRoom();
    void deleteHotelRoom(HotelRoom room);
    List<HotelRoom> searchHotelRoomByParameter(Parameter parameter);
}
