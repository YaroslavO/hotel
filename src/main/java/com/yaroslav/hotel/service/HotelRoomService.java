package com.yaroslav.hotel.service;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;

import java.util.Date;
import java.util.List;

/**
 * Created by PC on 05.06.2015.
 */
public interface HotelRoomService {
    void reservation(HotelRoom room, Date date) throws ReflectiveOperationException;
    void reservation(HotelRoom room, List<Date> dates) throws ReservationHotelRoomException;
    List<HotelRoom> getAllRoom();
    void addHotelRoom(HotelRoom room);
    List<HotelRoom> searchHotelRoomByParameter(Parameter parameter);
}
