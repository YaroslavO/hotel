package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.Reservation;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Parameter;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by PC on 05.06.2015.
 */

@Service
@Transactional
public class HotelRoomServiceImpl implements HotelRoomService {

    private HotelRoomDao hotelRoom;

    @Autowired
    public HotelRoomServiceImpl(HotelRoomDao hotelRoom) {
        this.hotelRoom = hotelRoom;
    }

    @Override
    public List<HotelRoom> getAllRoom() {
        return hotelRoom.getAllHotelRoom();
    }

    @Override
    public void addHotelRoom(HotelRoom room) {
        hotelRoom.addHotelRoom(room);
    }

    @Override
    public List<HotelRoom> searchHotelRoomByParameter(Parameter parameter) {
        return hotelRoom.searchHotelRoomByParameter(parameter);
    }

    @Override
    public void reservation(HotelRoom room, Date date) throws ReflectiveOperationException {
        Reservation reservation = new Reservation();
        reservation.setDate(date);

        List<Reservation> oneDayReservation = new ArrayList<>();
        oneDayReservation.add(reservation);

        if (room.getReservationPeriod() != null) {
            List<Reservation> oldReservation = new ArrayList<>();
            oldReservation.addAll(room.getReservationPeriod());
            if (oldReservation.containsAll(oneDayReservation)) {
                throw new ReflectiveOperationException();
            }
        }

        room.setReservationPeriod(oneDayReservation);
        hotelRoom.updateHotelRoom(room);
    }

    @Override
    public void reservation(HotelRoom room, List<Date> dates) throws ReservationHotelRoomException {
        List<Reservation> daysReservation = new ArrayList<>();
        List<Reservation> reservationPeriodBefore = new ArrayList<>();

        for (Date dateForReservation: dates) {
            Reservation reservation = new Reservation();
            reservation.setDate(dateForReservation);
            daysReservation.add(reservation);
        }

        if (room.getReservationPeriod() != null) {
            if (reservationPeriodBefore.containsAll(daysReservation)) {
                throw new ReservationHotelRoomException();
            }

            reservationPeriodBefore = room.getReservationPeriod();
            reservationPeriodBefore.addAll(daysReservation);
            room.setReservationPeriod(reservationPeriodBefore);
        } else {
            room.setReservationPeriod(daysReservation);
        }

        hotelRoom.updateHotelRoom(room);
    }


}
