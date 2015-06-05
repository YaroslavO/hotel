package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.ClassHotelRoom;
import com.yaroslav.hotel.entity.DateReservation;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.TypeHotelRoom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Created by employee on 6/5/15.
 */

public class HotelRoomDaoTest extends AbstractDaoTest{

    @Autowired
    private HotelRoomDao hotelRoom;

    @Test
    @Rollback(true)
    public void createNewHotelRoom() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(ClassHotelRoom.ECONOM);
        room.setType(TypeHotelRoom.DBL);
        hotelRoom.addHotelRoom(room);
        assertThat(room.getId(), not(comparesEqualTo(null)));
    }

    @Test
    @Rollback(true)
    public void getAllHotelRoom() throws Exception {
        List<HotelRoom> rooms = hotelRoom.getAllRoom();

        createHotelRoom(TypeHotelRoom.SGL, ClassHotelRoom.LUX);
        createHotelRoom(TypeHotelRoom.DBL, ClassHotelRoom.STANDARD);
        List<HotelRoom> roomsAfterCreate = hotelRoom.getAllRoom();

        assertThat(roomsAfterCreate, not(rooms));
    }

    private void createHotelRoom(TypeHotelRoom typeRoom, ClassHotelRoom classRoom) {
        HotelRoom room = new HotelRoom();
        room.setType(typeRoom);
        room.setClassRoom(classRoom);
        hotelRoom.addHotelRoom(room);
    }

    @Test
    @Rollback(true)
    public void reservationRoomOnOneDay() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 9);
        Date dateReservation = calendar.getTime();

        HotelRoom room = new HotelRoom();
        room.setClassRoom(ClassHotelRoom.STANDARD);
        room.setType(TypeHotelRoom.SGL);
        hotelRoom.addHotelRoom(room);

        DateReservation dateReservationRoom = new DateReservation();
        dateReservationRoom.setDate(dateReservation);
        List<DateReservation> reservationPeriod = new ArrayList<>();
        reservationPeriod.add(dateReservationRoom);
        room.setReservationPeriod(reservationPeriod);

        assertThat(room.getReservationPeriod().size(), is(1));
        assertThat(room.getReservationPeriod().get(0).getId(), not(comparesEqualTo(null)));
    }
}
