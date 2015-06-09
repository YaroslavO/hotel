package com.yaroslav.hotel.service;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;
import com.yaroslav.hotel.entity.Period;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;

import java.util.Date;
import java.util.List;

/**
 * Created by PC on 05.06.2015.
 */
public interface HotelRoomService {
    List<HotelRoom> searchHotelRoomByParameter(Parameter parameter);
    void reservation(HotelRoom room, Date date) throws ReservationHotelRoomException;
    List<HotelRoom> getAllRoom();
    void addHotelRoom(HotelRoom room);
    void reservation(HotelRoom room, Period period) throws ReservationHotelRoomException;
}
