package com.yaroslav.hotel.service;

import com.yaroslav.hotel.dao.AbstractDataBaseTest;
import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.dao.ReservationDao;
import com.yaroslav.hotel.entity.*;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by employee on 6/9/15.
 */

public class ReceptionTest extends AbstractDataBaseTest {

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Autowired
    private ReservationDao reservationDao;

    private Reception reception;

    @Before
    public void setUp() throws Exception {

        reservationDao = mock(ReservationDao.class);

        reception = new Reception(reservationDao);

        when(reservationDao.save(any(Reservation.class))).thenAnswer(new Answer<Reservation>() {

            @Override
            public Reservation answer(InvocationOnMock invocationOnMock) throws Throwable {
                Reservation reservation = (Reservation) invocationOnMock.getArguments()[0];
                reservation.setId(1);
                return reservation;
            }
        });
    }

    @Test(expected = ReservationHotelRoomException.class)
    public void reservationRoomOnOneDay() throws Exception {
        //given
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.STANDARD);
        room.setType(SizeRoomType.SGL);
        hotelRoomDao.saveHotelRoom(room);

        //when
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 9);

        //than
        Reservation reservation = reception.reservation(room, new Period(calendar.getTime()));
        verify(reservationDao).save(reservation);
        reception.reservation(room, new Period(calendar.getTime()));
    }

    @Test(expected = ReservationHotelRoomException.class)
    public void reservationRoomOnCustomCountDay() throws Exception {
        //given
        HotelRoom room = new HotelRoom();
        room.setClassRoom(BudgetRoomType.STANDARD);
        room.setType(SizeRoomType.SGL);
        hotelRoomDao.saveHotelRoom(room);

        //when
        Calendar beginDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();
        beginDay.set(2015, Calendar.JUNE, 1);
        endDay.setTime(beginDay.getTime());
        endDay.set(Calendar.DAY_OF_MONTH, 5);
        Period period = new Period(beginDay.getTime(), endDay.getTime());

        //that
        Reservation reservation = reception.reservation(room, period);
        verify(reservationDao).save(reservation);
        reception.reservation(room, period);
    }

}