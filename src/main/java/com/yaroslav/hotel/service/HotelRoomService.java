package com.yaroslav.hotel.service;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;

import java.util.List;

/**
 * Created by PC on 05.06.2015.
 */
public interface HotelRoomService {
    HotelRoom getHotelRoomById(Integer id);

    List<HotelRoom> searchHotelRoomByParameter(Parameter parameter);

    List<HotelRoom> getAllRoom();

    void addHotelRoom(HotelRoom room);
}
