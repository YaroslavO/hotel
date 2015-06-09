package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.Period;
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
    public HotelRoom getHotelRoomById(Integer id) {
        return hotelRoom.getHotelRoomById(id);
    }

    @Override
    public List<HotelRoom> searchHotelRoomByParameter(Parameter parameter) {
        return hotelRoom.searchHotelRoomByParameter(parameter);
    }

    @Override
    public void reservation(HotelRoom room, Date date) throws ReservationHotelRoomException {
        Period period = new Period(date);
        Reservation reservation = new Reservation(period);

        List<Reservation> reservations = new ArrayList<>();
        reservations.add(reservation);

        if (room.getReservation() != null) {
            List<Reservation> oldReservation = new ArrayList<>();
            oldReservation.addAll(room.getReservation());
            if (oldReservation.containsAll(reservations)) {
                throw new ReservationHotelRoomException();
            }

            oldReservation.addAll(reservations);
            room.setReservation(oldReservation);
            hotelRoom.updateHotelRoom(room);
            return;
        } else {
            room.setReservation(reservations);
        }

        hotelRoom.updateHotelRoom(room);
    }

    @Override
    public void reservation(HotelRoom room, Period period) throws ReservationHotelRoomException {
        List<Reservation> daysReservation = new ArrayList<>();
        List<Reservation> oldReservationPeriodBefore = new ArrayList<>();

        Reservation reservation = new Reservation(period);
        daysReservation.add(reservation);

        if (room.getReservation() != null) {
            if (oldReservationPeriodBefore.containsAll(daysReservation)) {
                throw new ReservationHotelRoomException();
            }

            oldReservationPeriodBefore = room.getReservation();
            oldReservationPeriodBefore.addAll(daysReservation);
            room.setReservation(oldReservationPeriodBefore);
        } else {
            room.setReservation(daysReservation);
        }

        hotelRoom.updateHotelRoom(room);
    }


}
