package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;

import java.util.List;

/**
 * Created by employee on 6/5/15.
 */

public interface HotelRoomDao {
    void addHotelRoom(HotelRoom hotelRoom);
    List<HotelRoom> getAllRoom();
    void deleteHotelRoom(HotelRoom room);
}
