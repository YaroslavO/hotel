package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.Reservation;

/**
 * Created by employee on 6/8/15.
 */
public interface ReservationDao {
    void save(Reservation oldReservation);
}
