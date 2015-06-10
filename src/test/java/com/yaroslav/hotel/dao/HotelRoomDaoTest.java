package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.config.AbstractDataBaseTest;
import com.yaroslav.hotel.entity.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

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
        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);

        assertNull(room.getId());

        hotelRoom.saveHotelRoom(room);

        assertNotNull(room.getId());
        assertTrue(room.getId() > 0);
    }

    @Test
    public void getAllHotelRoom() throws Exception {
        List<HotelRoom> rooms = hotelRoom.getAllHotelRoom();

        makeHotelRoom();
        List<HotelRoom> roomsAfterCreate = hotelRoom.getAllHotelRoom();

        assertNotEquals(rooms, roomsAfterCreate);
    }

    private void makeHotelRoom() {
        HotelRoom room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);
        room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.STANDARD);
        hotelRoom.saveHotelRoom(room);
    }

    @Test
    public void deleteRoomHotelById() throws Exception {
        //given
        HotelRoom room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.saveHotelRoom(room);

        //when
        assertThat(room.getId(), not(comparesEqualTo(null)));
        hotelRoom.deleteHotelRoom(room);

//        than
        assertThat(hotelRoom.getAllHotelRoom().size(), is(0));
    }

    @Test
    public void searchHotelRoomByDate() throws Exception {
        Period period = createPeriod(2015, Calendar.JUNE, 13);
        hqlBuilder.setPeriod(period);

        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);

        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);

        Reservation reservation = new Reservation(period);
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
        Period period = createPeriod(2015, Calendar.JUNE, 13);
        hqlBuilder.setPeriod(period);

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
        Period period = createPeriod(2015, Calendar.JUNE, 13);
        hqlBuilder.setPeriod(period);

        makeHotelRoomForSizeSearch();

        //when
        hqlBuilder.setSizeRoomType(SizeRoomType.SGL);
        List<HotelRoom> rooms;

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

    private void makeHotelRoomForSizeSearch() {
        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);
        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);
        room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
        hotelRoom.saveHotelRoom(room);
    }

    private Period createPeriod(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return new Period(calendar.getTime());
    }
}
