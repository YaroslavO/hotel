package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.AbstractDataBaseTest;
import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.dao.ReservationDao;
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
    private ReservationDao reservationDao;
    private HotelRoomService hotelRoomService;
    private Calendar dayInCalendar;

    @Before
    public void setUp() throws Exception {
        hotelRoomDao = mock(HotelRoomDao.class);
        reservationDao = mock(ReservationDao.class);
        hotelRoomService = new HotelRoomServiceImpl(hotelRoomDao, reservationDao);
        dayInCalendar = Calendar.getInstance();
        dayInCalendar.set(Calendar.YEAR, 2015);

        when(hotelRoomDao.updateHotelRoom(any(HotelRoom.class))).thenAnswer(new Answer<HotelRoom>() {

            @Override
            public HotelRoom answer(InvocationOnMock invocationOnMock) throws Throwable {
                HotelRoom hotelRoom = (HotelRoom) invocationOnMock.getArguments()[0];
                return hotelRoom;
            }
        });

        when(reservationDao.save(any(Reservation.class))).thenAnswer(new Answer<Reservation>() {

            @Override
            public Reservation answer(InvocationOnMock invocationOnMock) throws Throwable {
                Reservation reservation = (Reservation) invocationOnMock.getArguments()[0];
                return reservation;
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
        assertThat(hotelRoom.getReservation(), not(comparesEqualTo(null)));

        Calendar startDate = Calendar.getInstance();
        startDate.setTime(hotelRoom.getReservation().get(0).getStartDate());
        Calendar endDate = Calendar.getInstance();
        endDate.setTime(hotelRoom.getReservation().get(0).getEndDate());
        assertThat(startDate.get(Calendar.DAY_OF_YEAR), is(dayInCalendar.get(Calendar.DAY_OF_YEAR)));
        assertThat(endDate.get(Calendar.DAY_OF_YEAR), is(dayInCalendar.get(Calendar.DAY_OF_YEAR)));
    }

    @Test
    public void reservationHotelRoomOnSomeCountDay() throws Exception {
        //given
        Period period = makePeriod(1, 6);

        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.setId(1);

        //when
        hotelRoomService.reservation(hotelRoom, period);

        //than
        assertThat(hotelRoom.getReservation(), not(comparesEqualTo(null)));
        assertTrue(hotelRoom.getReservation().size() == 1);
        assertThat(hotelRoom.getReservation().get(0).getStartDate(), is(period.begin));
        assertThat(hotelRoom.getReservation().get(0).getEndDate(), is(period.end));
    }

    @Test
    public void reservationHotelRoomWithExistsReservationPeriod() throws Exception {
        //given
        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.setId(1);
        prepareHotelRoomWithExistingPeriod(hotelRoom);

        List<Reservation> existingPeriod = new ArrayList<>();
        existingPeriod.addAll(hotelRoom.getReservation());

        Period period = makePeriod(7, 12);

        //when
        hotelRoomService.reservation(hotelRoom, period);
        List<Reservation> allPeriod = new ArrayList<>();
        allPeriod.addAll(hotelRoom.getReservation());

        //than
        assertThat(existingPeriod.size(), not(allPeriod.size()));
        assertThat(existingPeriod, not(equalTo(allPeriod)));
        assertTrue(allPeriod.containsAll(existingPeriod));
    }

    private Period makePeriod(Integer begin, Integer end) {
        dayInCalendar.set(Calendar.DAY_OF_YEAR, begin);
        Date startDate = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
        dayInCalendar.set(Calendar.DAY_OF_YEAR, end);
        Date endDate = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
        Period period = new Period(startDate, endDate);
        return period;
    }

    private void prepareHotelRoomWithExistingPeriod(HotelRoom hotelRoom) throws ReservationHotelRoomException {
        Period period = makePeriod(1, 6);

        hotelRoomService.reservation(hotelRoom, period);
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
}
