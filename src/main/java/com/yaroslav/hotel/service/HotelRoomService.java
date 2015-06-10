package com.yaroslav.hotel.service;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;
import com.yaroslav.hotel.exception.SearchNullParameterException;

import java.util.List;

/**
 * Created by PC on 05.06.2015.
 */
public interface HotelRoomService {
    HotelRoom getHotelRoomById(Integer id);

    List<HotelRoom> searchHotelRoomByParameter(Parameter parameter) throws SearchNullParameterException;

    List<HotelRoom> getAllRoom();

    void addHotelRoom(HotelRoom room);
}
