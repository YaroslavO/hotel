package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.ClassHotelRoom;
import com.yaroslav.hotel.entity.DateReservation;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.TypeHotelRoom;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by employee on 6/5/15.
 */

public class HotelRoomDataBaseTest extends AbstractDataBaseTest {

    @Autowired
    private HotelRoomDao hotelRoom;

    @Test
    public void createNewHotelRoom() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(ClassHotelRoom.ECONOM);
        room.setType(TypeHotelRoom.DBL);

        assertNull(room.getId());

        hotelRoom.addHotelRoom(room);

        assertNotNull(room.getId());
        assertTrue(room.getId() > 0);
    }

    @Test
    public void getAllHotelRoom() throws Exception {
        List<HotelRoom> rooms = hotelRoom.getAllHotelRoom();

        createHotelRoom(TypeHotelRoom.SGL, ClassHotelRoom.LUX);
        createHotelRoom(TypeHotelRoom.DBL, ClassHotelRoom.STANDARD);
        List<HotelRoom> roomsAfterCreate = hotelRoom.getAllHotelRoom();

        assertNotEquals(rooms, roomsAfterCreate);
    }

    private void createHotelRoom(TypeHotelRoom typeRoom, ClassHotelRoom classRoom) {
        HotelRoom room = new HotelRoom();
        room.setType(typeRoom);
        room.setClassRoom(classRoom);
        hotelRoom.addHotelRoom(room);
    }

    @Test
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
        assertThat(room.getReservationPeriod().get(0).getDate(), is(dateReservation));
    }

    @Test
    public void reservationRoomOnCustomCountDay() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(ClassHotelRoom.STANDARD);
        room.setType(TypeHotelRoom.SGL);
        hotelRoom.addHotelRoom(room);

        Calendar calendar = Calendar.getInstance();
        List<DateReservation> reservationPeriod = new ArrayList<>();

        for (int numberDate = 1; numberDate <= 7; numberDate++) {
            DateReservation dateReservationRoom = new DateReservation();
            calendar.set(2015, Calendar.JUNE, numberDate);
            Date dateReservation = calendar.getTime();
            dateReservationRoom.setDate(dateReservation);
            reservationPeriod.add(dateReservationRoom);
        }
        room.setReservationPeriod(reservationPeriod);

        assertThat(room.getReservationPeriod().get(0).getId(), not(comparesEqualTo(null)));
        assertThat(room.getReservationPeriod().size(), is(7));
        assertThat(room.getReservationPeriod(), is(reservationPeriod));
    }

    @Test
    public void deleteRoomHotelById() throws Exception {
        //given
        HotelRoom room = new HotelRoom();

        //when
        room.setClassRoom(ClassHotelRoom.ECONOM);
        room.setType(TypeHotelRoom.SGL);
        hotelRoom.addHotelRoom(room);

//        than
        assertThat(room.getId(), not(comparesEqualTo(null)));

        hotelRoom.deleteHotelRoom(room);

        assertThat(hotelRoom.getAllHotelRoom().size(), is(0));
    }
}
