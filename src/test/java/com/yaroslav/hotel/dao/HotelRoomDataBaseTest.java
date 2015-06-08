package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.*;
import com.yaroslav.hotel.util.HotelHelper;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by employee on 6/5/15.
 */

public class HotelRoomDataBaseTest extends AbstractDataBaseTest {

    @Autowired
    private HotelRoomDao hotelRoom;

    @Autowired
    private ReservationDao reservationDao;

    @Test
    public void createNewHotelRoom() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.ECONOM);
        room.setType(SizeRoomType.DBL);

        assertNull(room.getId());

        hotelRoom.addHotelRoom(room);

        assertNotNull(room.getId());
        assertTrue(room.getId() > 0);
    }

    @Test
    public void getAllHotelRoom() throws Exception {
        List<HotelRoom> rooms = hotelRoom.getAllHotelRoom();

        createHotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        createHotelRoom(SizeRoomType.DBL, BudgetRoomType.STANDARD);
        List<HotelRoom> roomsAfterCreate = hotelRoom.getAllHotelRoom();

        assertNotEquals(rooms, roomsAfterCreate);
    }

    private void createHotelRoom(SizeRoomType typeRoom, BudgetRoomType classRoom) {
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
        room.setClassRoom(BudgetRoomType.STANDARD);
        room.setType(SizeRoomType.SGL);
        hotelRoom.addHotelRoom(room);

        Period period = new Period(dateReservation);
        Reservation ReservationRoom = new Reservation(period);
        List<Reservation> ReservationPeriod = new ArrayList<>();
        ReservationPeriod.add(ReservationRoom);
        room.setReservation(ReservationPeriod);

        assertThat(room.getReservation().size(), is(1));
        assertThat(room.getReservation().get(0).getId(), not(comparesEqualTo(null)));
    }

    @Test
    public void reservationRoomOnCustomCountDay() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.STANDARD);
        room.setType(SizeRoomType.SGL);
        hotelRoom.addHotelRoom(room);

        Calendar beginDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();
        beginDay.set(2015, Calendar.JUNE, 1);
        endDay.setTime(beginDay.getTime());
        endDay.set(Calendar.DAY_OF_MONTH, 5);
        Period period = new Period(beginDay.getTime(), endDay.getTime());
        List<Reservation> reservationPeriod = new ArrayList<>();
        Reservation reservation = new Reservation(period);
        reservationPeriod.add(reservation);
        room.setReservation(reservationPeriod);

        assertThat(room.getReservation().get(0).getId(), not(comparesEqualTo(null)));
        assertThat(room.getReservation().size(), is(1));
    //    assertThat(room.getReservation().get(0), is(reservation));
    }

    @Test
    public void deleteRoomHotelById() throws Exception {
        //given
        HotelRoom room = new HotelRoom();

        //when
        room.setClassRoom(BudgetRoomType.ECONOM);
        room.setType(SizeRoomType.SGL);
        hotelRoom.addHotelRoom(room);

//        than
        assertThat(room.getId(), not(comparesEqualTo(null)));

        hotelRoom.deleteHotelRoom(room);

        assertThat(hotelRoom.getAllHotelRoom().size(), is(0));
    }

    @Test
    public void searchHotelRoomByDate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 13);
        Parameter dateParameter = new Parameter();
        Date date = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(calendar);
        dateParameter.period = new Period(date);

        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
        hotelRoom.addHotelRoom(room);

        List<Reservation> reservationPeriod = new ArrayList<>();
        Period period = new Period(date);
        Reservation reservation = new Reservation(period);

        reservationDao.save(reservation);

        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        reservationPeriod.add(reservation);
        room.setReservation(reservationPeriod);
        hotelRoom.addHotelRoom(room);

        reservation.setHotelRoom(room);

        List<HotelRoom> rooms = hotelRoom.searchHotelRoomByParameter(dateParameter);

        assertNotNull(rooms);
        assertNotNull(rooms.get(0));
        assertTrue(rooms.size() == 1);
    }
}
