package com.yaroslav.hotel.service;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Period;
import com.yaroslav.hotel.entity.Reservation;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;

/**
 * Created by employee on 6/12/15.
 */
public interface ReceptionService {
    Reservation reservation(HotelRoom room, Period period) throws ReservationHotelRoomException;
}
