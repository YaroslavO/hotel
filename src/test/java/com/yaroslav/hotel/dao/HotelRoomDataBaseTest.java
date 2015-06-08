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

        Reservation reservationRoom = new Reservation();
        reservationRoom.setDate(dateReservation);
        List<Reservation> reservationPeriod = new ArrayList<>();
        reservationPeriod.add(reservationRoom);
        room.setReservationPeriod(reservationPeriod);

        assertThat(room.getReservationPeriod().size(), is(1));
        assertThat(room.getReservationPeriod().get(0).getId(), not(comparesEqualTo(null)));
        assertThat(room.getReservationPeriod().get(0).getDate(), is(dateReservation));
    }

    @Test
    public void reservationRoomOnCustomCountDay() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.STANDARD);
        room.setType(SizeRoomType.SGL);
        hotelRoom.addHotelRoom(room);

        Calendar calendar = Calendar.getInstance();
        List<Reservation> reservationPeriod = new ArrayList<>();

        for (int numberDate = 1; numberDate <= 7; numberDate++) {
            Reservation reservationRoom = new Reservation();
            calendar.set(2015, Calendar.JUNE, numberDate);
            Date dateReservation = calendar.getTime();
            reservationRoom.setDate(dateReservation);
            reservationPeriod.add(reservationRoom);
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
        Date firstDate = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(calendar);
        dateParameter.period = new Period(firstDate);

        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
        hotelRoom.addHotelRoom(room);

        List<Reservation> reservationPeriod = new ArrayList<>();
        Reservation reservation = new Reservation();
        reservation.setDate(firstDate);
        reservationDao.save(reservation);

        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        reservationPeriod.add(reservation);
        room.setReservationPeriod(reservationPeriod);
        hotelRoom.addHotelRoom(room);
        List<HotelRoom> reservationRooms = new ArrayList<>();
        reservationRooms.add(room);
        reservation.setRoom(reservationRooms);

        List<HotelRoom> rooms = hotelRoom.searchHotelRoomByParameter(dateParameter);

        assertNotNull(rooms);
    //    assertNotNull(rooms.get(0));
//        assertTrue(rooms.size() == 1);
    }
}
