package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Period;
import com.yaroslav.hotel.entity.Reservation;

/**
 * Created by employee on 6/8/15.
 */
public interface ReservationDao {
    Reservation save(Reservation reservation);

    boolean canBeReserved(HotelRoom room, Period period);
}
