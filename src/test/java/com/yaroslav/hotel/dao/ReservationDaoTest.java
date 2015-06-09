package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.*;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.comparesEqualTo;
import static org.hamcrest.Matchers.not;

/**
 * Created by employee on 6/9/15.
 */
public class ReservationDaoTest extends AbstractDataBaseTest {

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Test
    public void saveReservation() throws Exception {
        HotelRoom room = new HotelRoom( SizeRoomType.DBL, BudgetRoomType.ECONOM);
        hotelRoomDao.saveHotelRoom(room);

        Calendar date = Calendar.getInstance();
        date.set(2015, Calendar.JUNE, 11);
        Reservation reservation = new Reservation(new Period(date.getTime()));
        reservation.setHotelRoom(room);
        reservationDao.save(reservation);

        assertThat(reservation.getId(), not(comparesEqualTo(null)));
        assertThat(reservation.getHotelRoom().getId(), is(room.getId()));
    }
}