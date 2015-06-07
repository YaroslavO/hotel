package com.yaroslav.hotel.service;

import com.yaroslav.hotel.AbstractDataBaseTest;
import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.ClassHotelRoom;
import com.yaroslav.hotel.entity.DateReservation;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.TypeHotelRoom;
import com.yaroslav.hotel.util.hotelHelper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.core.Is.is;

/**
 * Created by PC on 05.06.2015.
 */

public class HotelRoomServiceTest extends AbstractDataBaseTest {

    public static final int COUNT_RESERVATIONS = 19;
    @Autowired
    private HotelRoomService hotelRoomService;

    @Autowired
    private HotelRoomDao hotelRoomDao;

    private Calendar calendar;
    private HotelRoom room;

    @Before
    public void setUp() throws Exception {
        calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.MARCH, 20);
        room = new HotelRoom();
        room.setType(TypeHotelRoom.SGL);
        room.setClassRoom(ClassHotelRoom.ECONOM);
    }

    @Test
    @Rollback(true)
    public void reservationHotelRoomForOneDay() throws Exception {
        //given
        Date date = hotelHelper.getDateWithHourMinuteSecondsMilisecondInZero(calendar);
        hotelRoomService.reservation(room, date);

        //when
        List<HotelRoom> rooms = hotelRoomService.getAllRoom();
        List<DateReservation> reservationPeriod = rooms.get(0).getReservationPeriod();

        //than
        assertThat(room.getReservationPeriod().size(), is(1));
        assertThat(date, is(reservationPeriod.get(0).getDate()));
    }

    // TODO try with mock
    @Test
    @Rollback(true)
    public void reservationHotelRoomForSomeCountDay() throws Exception {
        //given
        List<Date> dateReservations = new ArrayList<>();


        for (int numberReservation = 1; numberReservation <= COUNT_RESERVATIONS; numberReservation++) {
            calendar.set(Calendar.DAY_OF_YEAR, numberReservation);
            Date date = hotelHelper.getDateWithHourMinuteSecondsMilisecondInZero(calendar);
            dateReservations.add(date);
        }

        //when
        hotelRoomDao.addHotelRoom(room);
        hotelRoomService.reservation(room, dateReservations);
        room = hotelRoomService.getAllRoom().get(0);
        List<DateReservation> allHotelRoomDateReservation = room.getReservationPeriod();

        //than
        for (int numberReservation = 1; numberReservation <= dateReservations.size(); numberReservation++) {
            assertThat(dateReservations.get(numberReservation),
                    is(allHotelRoomDateReservation.get(numberReservation).getDate()));
        }

    }
}
