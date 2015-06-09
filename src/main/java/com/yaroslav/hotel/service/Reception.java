package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.ReservationDao;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Period;
import com.yaroslav.hotel.entity.Reservation;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by employee on 6/9/15.
 */

@Service
@Transactional
public class Reception {

    private ReservationDao reservationDao;

    @Autowired
    public Reception(ReservationDao reservationDao) {
        this.reservationDao = reservationDao;
    }

    public Reservation reservation(HotelRoom room, Period period) throws ReservationHotelRoomException {
        Reservation reservation = new Reservation(period);

        if (reservationDao.isGoodPeriodForThisRoom(room, period)) {
            reservation.setHotelRoom(room);
            reservationDao.save(reservation);
        } else {
            throw new ReservationHotelRoomException();
        }

        return reservation;
    }
}
