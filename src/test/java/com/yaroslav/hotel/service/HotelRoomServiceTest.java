//package com.yaroslav.hotel.service;
//
//import com.yaroslav.hotel.dao.AbstractDataBaseTest;
//import com.yaroslav.hotel.dao.HotelRoomDao;
//import com.yaroslav.hotel.dao.ReservationDao;
//import com.yaroslav.hotel.entity.*;
//import com.yaroslav.hotel.exception.ReservationHotelRoomException;
//import com.yaroslav.hotel.util.HotelHelper;
//import org.junit.Assert;
//import org.junit.Before;
//import org.junit.Test;
//import org.mockito.invocation.InvocationOnMock;
//import org.mockito.stubbing.Answer;
//
//import java.util.ArrayList;
//import java.util.Calendar;
//import java.util.Date;
//import java.util.List;
//
//import static org.hamcrest.MatcherAssert.assertThat;
//import static org.hamcrest.Matchers.*;
//import static org.junit.Assert.assertTrue;
//import static org.mockito.Matchers.any;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
///**
// * Created by PC on 05.06.2015.
// */
//
//public class HotelRoomServiceTest extends AbstractDataBaseTest {
//
//    private HotelRoomDao hotelRoomDao;
//    private ReservationDao reservationDao;
//    private HotelRoomService hotelRoomService;
//    private Calendar dayInCalendar;
//
//    @Before
//    public void setUp() throws Exception {
//        hotelRoomDao = mock(HotelRoomDao.class);
//        reservationDao = mock(ReservationDao.class);
//        hotelRoomService = new HotelRoomServiceImpl(hotelRoomDao);
//        dayInCalendar = Calendar.getInstance();
//        dayInCalendar.set(Calendar.YEAR, 2015);
//
//        when(hotelRoomDao.updateHotelRoom(any(HotelRoom.class))).thenAnswer(new Answer<HotelRoom>() {
//
//            @Override
//            public HotelRoom answer(InvocationOnMock invocationOnMock) throws Throwable {
//                HotelRoom hotelRoom = (HotelRoom) invocationOnMock.getArguments()[0];
//                return hotelRoom;
//            }
//        });
//
//        when(reservationDao.save(any(Reservation.class))).thenAnswer(new Answer<Reservation>() {
//
//            @Override
//            public Reservation answer(InvocationOnMock invocationOnMock) throws Throwable {
//                Reservation reservation = (Reservation) invocationOnMock.getArguments()[0];
//                return reservation;
//            }
//        });
//    }
//
//
//}
