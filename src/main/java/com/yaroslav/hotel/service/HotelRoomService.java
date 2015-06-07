package com.yaroslav.hotel.service;

import com.yaroslav.hotel.entity.HotelRoom;

import java.util.Date;
import java.util.List;

/**
 * Created by PC on 05.06.2015.
 */
public interface HotelRoomService {
    void reservation(HotelRoom room, Date date);
    void reservation(HotelRoom room, List<Date> dates);
    List<HotelRoom> getAllRoom();
    void addHotelRoom(HotelRoom room);
}
