package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.DateReservation;
import com.yaroslav.hotel.entity.HotelRoom;
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
public class HotleRoomServiceImpl implements HotelRoomService {

    @Autowired
    private HotelRoomDao hotelRoom;

    @Override
    public List<HotelRoom> getAllRoom() {
        return hotelRoom.getAllHotelRoom();
    }

    @Override
    public void addHotelRoom(HotelRoom room) {
        hotelRoom.addHotelRoom(room);
    }

    @Override
    public void reservation(HotelRoom room, Date date) {
        DateReservation dateReservation = new DateReservation();
        dateReservation.setDate(date);

        List<DateReservation> oneDayReservation = new ArrayList<>();
        oneDayReservation.add(dateReservation);
        room.setReservationPeriod(oneDayReservation);
        hotelRoom.updateHotelRoom(room);
    }

    @Override
    public void reservation(HotelRoom room, List<Date> dates) {
        List<DateReservation> daysReservation = new ArrayList<>();
//        List<DateReservation> reservationPeriodBefore = new ArrayList<>();

        for (Date dateForReservation: dates) {
            DateReservation dateReservation = new DateReservation();
            dateReservation.setDate(dateForReservation);
            daysReservation.add(dateReservation);
        }

        room.setReservationPeriod(daysReservation);
        hotelRoom.updateHotelRoom(room);
//        if (room.getReservationPeriod() != null) {
//            reservationPeriodBefore = room.getReservationPeriod();
//        }
//
//        reservationPeriodBefore.addAll(daysReservation);

    }


}
