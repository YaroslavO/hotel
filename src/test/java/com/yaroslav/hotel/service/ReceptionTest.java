package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.AbstractDataBaseTest;
import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.dao.ReservationDao;
import com.yaroslav.hotel.entity.*;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

/**
 * Created by employee on 6/9/15.
 */

public class ReceptionTest extends AbstractDataBaseTest {

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Autowired
    private ReservationDao reservationDao;

    private Reception reception;

    private Calendar dayInCalendar;


    @Before
    public void setUp() throws Exception {
        dayInCalendar = Calendar.getInstance();

        reservationDao = mock(ReservationDao.class);

        reception = new Reception(reservationDao);
    }

    @Test
    public void reservationRoomOnOneDay() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.STANDARD);
        room.setType(SizeRoomType.SGL);
        hotelRoomDao.addHotelRoom(room);

        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 9);
        Date dateReservation = calendar.getTime();

        Reservation reservation = new Reservation(new Period(dateReservation));
        reservation.setHotelRoom(room);
        reservationDao.save(reservation);

        assertThat(reservation.getId(), not(comparesEqualTo(null)));
    }

    @Test
    public void reservationRoomOnCustomCountDay() throws Exception {
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.STANDARD);
        room.setType(SizeRoomType.SGL);
        hotelRoomDao.addHotelRoom(room);

        Calendar beginDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();
        beginDay.set(2015, Calendar.JUNE, 1);
        endDay.setTime(beginDay.getTime());
        endDay.set(Calendar.DAY_OF_MONTH, 5);
        Period period = new Period(beginDay.getTime(), endDay.getTime());

        Reservation reservation = new Reservation(period);
        reservation.setHotelRoom(room);
        reservationDao.save(reservation);

        assertThat(reservation.getId(), not(comparesEqualTo(null)));
    }

    @Test(expected = ReservationHotelRoomException.class)
    public void reservationHotelRoomOnOneDay() throws Exception {
        //given
        dayInCalendar.set(2015, Calendar.JUNE, 24);
        Date dateReservation = dayInCalendar.getTime();
        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
        hotelRoom.setId(1);

        reception.reservation(hotelRoom, new Period(dateReservation));

        //than


    }
//
//    @Test
//    public void reservationHotelRoomOnSomeCountDay() throws Exception {
//        //given
//        Period period = makePeriod(1, 6);
//
//        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
//        hotelRoom.setId(1);
//
//        //when
//        hotelRoomService.reservation(hotelRoom, period);
//
//        //than
//        MatcherAssert.assertThat(hotelRoom.getReservation(), not(comparesEqualTo(null)));
//        assertTrue(hotelRoom.getReservation().size() == 1);
//        MatcherAssert.assertThat(hotelRoom.getReservation().get(0).getStartDate(), is(period.begin));
//        MatcherAssert.assertThat(hotelRoom.getReservation().get(0).getEndDate(), is(period.end));
//    }
//
//    @Test
//    public void reservationHotelRoomWithExistsReservationPeriod() throws Exception {
//        //given
//        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
//        hotelRoom.setId(1);
//        prepareHotelRoomWithExistingPeriod(hotelRoom);
//
//        List<Reservation> existingPeriod = new ArrayList<>();
//        existingPeriod.addAll(hotelRoom.getReservation());
//
//        Period period = makePeriod(7, 12);
//
//        //when
//        hotelRoomService.reservation(hotelRoom, period);
//        List<Reservation> allPeriod = new ArrayList<>();
//        allPeriod.addAll(hotelRoom.getReservation());
//
//        //than
//        MatcherAssert.assertThat(existingPeriod.size(), not(allPeriod.size()));
//        MatcherAssert.assertThat(existingPeriod, not(equalTo(allPeriod)));
//        assertTrue(allPeriod.containsAll(existingPeriod));
//    }
//
//    private Period makePeriod(Integer begin, Integer end) {
//        dayInCalendar.set(Calendar.DAY_OF_YEAR, begin);
//        Date startDate = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
//        dayInCalendar.set(Calendar.DAY_OF_YEAR, end);
//        Date endDate = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
//        Period period = new Period(startDate, endDate);
//        return period;
//    }
//
//    private void prepareHotelRoomWithExistingPeriod(HotelRoom hotelRoom) throws ReservationHotelRoomException {
//        Period period = makePeriod(1, 6);
//
//        hotelRoomService.reservation(hotelRoom, period);
//    }
//
//    @Test
//    public void attemptToReserveTheRoomTwiceInOneDay() throws Exception {
//        //given
//        dayInCalendar.set(2015, Calendar.JUNE, 24);
//        Date dateReservation = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
//        HotelRoom hotelRoom = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
//        hotelRoom.setId(1);
//
//        hotelRoomService.reservation(hotelRoom, dateReservation);
//
//        //when
//        Date newDateReservation = HotelHelper.getDateWithHourMinuteSecondsMillisecondInZero(dayInCalendar);
//
//        //than
//        try {
//            hotelRoomService.reservation(hotelRoom, newDateReservation);
//        } catch (Exception e) {
//            Assert.assertThat(e, instanceOf(ReservationHotelRoomException.class));
//        }
//    }
}