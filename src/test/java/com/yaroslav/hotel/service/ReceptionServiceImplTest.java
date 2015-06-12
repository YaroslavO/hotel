package com.yaroslav.hotel.service;

import com.yaroslav.hotel.config.AbstractDataBaseTest;
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

public class ReceptionServiceImplTest extends AbstractDataBaseTest {

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Autowired
    private ReservationDao reservationDao;

    private ReceptionServiceImpl receptionServiceImpl;

    @Before
    public void setUp() throws Exception {

        reservationDao = mock(ReservationDao.class);

        receptionServiceImpl = new ReceptionServiceImpl(reservationDao);

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
        HotelRoom room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.STANDARD);
        hotelRoomDao.saveHotelRoom(room);

        //when
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.JUNE, 9);

        //than
        Reservation reservation = receptionServiceImpl.reservation(room, new Period(calendar.getTime()));
        verify(reservationDao).save(reservation);
        receptionServiceImpl.reservation(room, new Period(calendar.getTime()));
    }

    @Test(expected = ReservationHotelRoomException.class)
    public void reservationRoomOnCustomCountDay() throws Exception {
        //given
        HotelRoom room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.STANDARD);
        hotelRoomDao.saveHotelRoom(room);

        //when
        Calendar beginDay = Calendar.getInstance();
        Calendar endDay = Calendar.getInstance();
        beginDay.set(2015, Calendar.JUNE, 1);
        endDay.setTime(beginDay.getTime());
        endDay.set(Calendar.DAY_OF_MONTH, 5);
        Period period = new Period(beginDay.getTime(), endDay.getTime());

        //that
        Reservation reservation = receptionServiceImpl.reservation(room, period);
        verify(reservationDao).save(reservation);
        receptionServiceImpl.reservation(room, period);
    }

}