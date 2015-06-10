package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

/**
 * Created by employee on 6/5/15.
 */

public class HotelRoomDaoTest extends AbstractDataBaseTest {

    @Autowired
    private HotelRoomDao hotelRoom;

    @Autowired
    private ReservationDao reservationDao;

    private HqlQueryHotelRoomSearchBuilder hqlBuilder;

    @Before
    public void setUp() throws Exception {
        hqlBuilder = new HqlQueryHotelRoomSearchBuilder();
    }

    @Test
    public void createNewHotelRoom() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.ECONOM);
        room.setType(SizeRoomType.DBL);

        assertNull(room.getId());

        hotelRoom.saveHotelRoom(room);

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
        hotelRoom.saveHotelRoom(room);
    }

    @Test
    public void deleteRoomHotelById() throws Exception {
        //given
        HotelRoom room = new HotelRoom();

        //when
        room.setClassRoom(BudgetRoomType.ECONOM);
        room.setType(SizeRoomType.SGL);
        hotelRoom.saveHotelRoom(room);

//        than
        assertThat(room.getId(), not(comparesEqualTo(null)));

        hotelRoom.deleteHotelRoom(room);

        assertThat(hotelRoom.getAllHotelRoom().size(), is(0));
    }

    @Test
    public void searchHotelRoomByDate() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 13);
        hqlBuilder.setPeriod(new Period(calendar.getTime()));

        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);

        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);

        Reservation reservation = new Reservation(new Period(calendar.getTime()));
        reservation.setHotelRoom(room);
        reservationDao.save(reservation);

        reservation.setHotelRoom(room);

        List<HotelRoom> rooms = hotelRoom.searchHotelRoomByParameter(hqlBuilder);

        assertNotNull(rooms);
        assertNotNull(rooms.get(0));
        assertTrue(rooms.size() == 1);
    }

    @Test
    public void searchHotelRoomByType() throws Exception {
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 13);

        hqlBuilder.setPeriod(new Period(calendar.getTime()));

        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);
        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);

        hqlBuilder.setNotThisBudgetRoomType(BudgetRoomType.LUX);

        //when
        List<HotelRoom> rooms = hotelRoom.searchHotelRoomByParameter(hqlBuilder);

        //than
        assertNotNull(rooms);
        assertTrue(rooms.size() == 0);

        //when
        hqlBuilder.setThisBudgetRoomType(true);
        rooms = hotelRoom.searchHotelRoomByParameter(hqlBuilder);

        //than
        assertTrue(rooms.size() == 2);
    }

    @Test
    public void searchHotelRoomBySize() throws Exception {
        //given
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 13);
        hqlBuilder.setPeriod(new Period(calendar.getTime()));

        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);
        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);
        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);

        //when
        hqlBuilder.setSizeRoomType(SizeRoomType.SGL);
        List<HotelRoom> rooms = new ArrayList<>();

        rooms = hotelRoom.searchHotelRoomByParameter(hqlBuilder);

        //than
        assertThat(rooms.size(), is(2));
        assertThat(rooms.get(0).getType(), is(SizeRoomType.SGL));

        //when
        hqlBuilder.setSizeRoomType(SizeRoomType.DBL);
        rooms = hotelRoom.searchHotelRoomByParameter(hqlBuilder);

        //than
        assertThat(rooms.size(), is(1));
        assertThat(rooms.get(0).getType(), is(SizeRoomType.DBL));
    }
}
