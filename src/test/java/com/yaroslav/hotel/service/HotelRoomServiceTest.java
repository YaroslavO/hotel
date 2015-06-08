package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.AbstractDataBaseTest;
import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.*;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;
import com.yaroslav.hotel.util.HotelHelper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by PC on 05.06.2015.
 */

public class HotelRoomServiceTest extends AbstractDataBaseTest {

    private HotelRoomDao hotelRoomDao;
    private HotelRoomService hotelRoomService;
    private Calendar dayInCalendar;

    @Before
    public void setUp() throws Exception {
        hotelRoomDao = mock(HotelRoomDao.class);
        hotelRoomService = new HotelRoomServiceImpl(hotelRoomDao);
        dayInCalendar = Calendar.getInstance();
        dayInCalendar.set(Calendar.YEAR, 2015);

        when(hotelRoomDao.updateHotelRoom(any(HotelRoom.class))).thenAnswer(new Answer<HotelRoom>() {

            @Override
            public HotelRoom answer(InvocationOnMock invocationOnMock) throws Throwable {
                HotelRoom hotelRoom = (HotelRoom) invocationOnMock.getArguments()[0];
                return hotelRoom;
            }
        });
    }

    @Test
    public void reservationHotelRoomOnOneDay() throws Exception {
        //given
        dayInCalendar.set(2015, Calendar.JUNE, 24);
        Date dateReservation = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.setId(1);

        hotelRoomService.reservation(hotelRoom, dateReservation);

        //than
        assertThat(hotelRoom.getReservationPeriod(), not(comparesEqualTo(null)));
        assertThat(hotelRoom.getReservationPeriod().get(0).getDate(), is(dateReservation));
    }

    @Test
    public void reservationHotelRoomOnSomeCountDay() throws Exception {
        //given
        List<Date> period = new ArrayList<>();

        for (int numberDay = 1; numberDay <= 20; numberDay++) {
            dayInCalendar.set(Calendar.DAY_OF_YEAR, numberDay);
            Date date = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
            period.add(date);
        }

        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.setId(1);

        //when
        hotelRoomService.reservation(hotelRoom, period);

        //than
        assertThat(hotelRoom.getReservationPeriod(), not(comparesEqualTo(null)));
        assertThat(hotelRoom.getReservationPeriod().size(), is(period.size()));

        for (int numberDay = 0; numberDay < period.size(); numberDay++) {
            assertThat(hotelRoom.getReservationPeriod().get(numberDay).getDate(), is(period.get(numberDay)));
        }
    }

    @Test
    public void reservationHotelRoomWithExistsReservationPeriod() throws Exception {
        //given
        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.setId(1);
        prepareHotelRoomWithExistingPeriod(hotelRoom);

        List<Reservation> existingPeriod = new ArrayList<>();
        existingPeriod.addAll(hotelRoom.getReservationPeriod());
        List<Date> periodForAdd = new ArrayList<>();

        for (int numberDay = 30; numberDay <= 40; numberDay++) {
            dayInCalendar.set(Calendar.DAY_OF_YEAR, numberDay);
            Date date = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
            periodForAdd.add(date);
        }

        //when
        hotelRoomService.reservation(hotelRoom, periodForAdd);
        List<Reservation> allPeriod = new ArrayList<>();
        allPeriod.addAll(hotelRoom.getReservationPeriod());

        //than
        assertThat(existingPeriod.size(), not(allPeriod.size()));
        assertThat(existingPeriod, not(equalTo(allPeriod)));
        assertTrue(allPeriod.containsAll(existingPeriod));
    }

    private void prepareHotelRoomWithExistingPeriod(HotelRoom hotelRoom) throws ReservationHotelRoomException {

        List<Date> existingPeriod = new ArrayList<>();

        for (int numberDay = 1; numberDay <= 20; numberDay++) {
            dayInCalendar.set(Calendar.DAY_OF_YEAR, numberDay);
            Date date = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
            existingPeriod.add(date);
        }

        //when
        hotelRoomService.reservation(hotelRoom, existingPeriod);
    }

    @Test
    public void attemptToReserveTheRoomTwiceInOneDay() throws Exception {
        //given
        dayInCalendar.set(2015, Calendar.JUNE, 24);
        Date dateReservation = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.setId(1);

        hotelRoomService.reservation(hotelRoom, dateReservation);

        //when
        Date newDateReservation = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);

        //than
        try {
            hotelRoomService.reservation(hotelRoom, newDateReservation);
        } catch (Exception e) {
            Assert.assertThat(e, instanceOf(ReservationHotelRoomException.class));
        }
    }

    @Test
    public void searchHotelRoomOnSomePeriod() throws Exception {

    }
}
