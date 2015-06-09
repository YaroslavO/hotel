package com.yaroslav.hotel.dao;

import com.yaroslav.hotel.entity.*;
import org.hibernate.SessionFactory;
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
    private SessionFactory sessionFactory;

    @Autowired
    private ReservationDao reservationDao;

    @Autowired
    private HotelRoomDao hotelRoomDao;

    @Test
    public void saveReservation() throws Exception {
        //given
        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);
        hotelRoomDao.saveHotelRoom(room);

        Calendar date = Calendar.getInstance();
        date.set(2015, Calendar.JUNE, 11);
        Reservation reservation = new Reservation(new Period(date.getTime()));
        reservation.setHotelRoom(room);

        //when
        reservationDao.save(reservation);
        sessionFactory.getCurrentSession().evict(reservation);
        reservation = (Reservation) sessionFactory.getCurrentSession().get(Reservation.class, reservation.getId());

        //then
        assertThat(reservation.getId(), not(comparesEqualTo(null)));
        assertThat(reservation.getHotelRoom().getId(), is(room.getId()));
    }

    @Test
    public void canNotReserve__roomReservedForTheSamePeriod() throws Exception {
        // given
        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);
        hotelRoomDao.saveHotelRoom(room);

        Period period = getPeriod();
        reservationDao.save(new Reservation(room, period));

        // when
        boolean canBeReserved = reservationDao.canBeReserved(room, period);

        // then
        assertThat(canBeReserved, is(false));
    }

    @Test
    public void canReserve__neverReservedRoom() throws Exception {
        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);
        hotelRoomDao.saveHotelRoom(room);

        boolean canBeReserved = reservationDao.canBeReserved(room, getPeriod());

        assertThat(canBeReserved, is(true));
    }

    private Period getPeriod() {
        Calendar dateStart = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateStart.set(2015, Calendar.JUNE, 11);
        dateEnd.set(2015, Calendar.JUNE, 12);
        return new Period(dateStart.getTime(), dateEnd.getTime());
    }

    @Test
    public void canReserve__roomReservedForTheSomeCountPeriod() throws Exception {
        HotelRoom room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);
        hotelRoomDao.saveHotelRoom(room);

        reservationDao.save(new Reservation(room, getPeriod()));

        Calendar dateStart = Calendar.getInstance();
        Calendar dateEnd = Calendar.getInstance();
        dateStart.set(2015, Calendar.JUNE, 14);
        dateEnd.set(2015, Calendar.JUNE, 15);

        boolean canBeReserved = reservationDao.canBeReserved(room, new Period(dateStart.getTime(), dateEnd.getTime()));

        assertThat(canBeReserved, is(true));
    }
}