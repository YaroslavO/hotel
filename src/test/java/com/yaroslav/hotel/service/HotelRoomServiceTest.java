package com.yaroslav.hotel.service;

import com.yaroslav.hotel.AbstractDataBaseTest;
import com.yaroslav.hotel.dao.HotelRoomDao;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;

import java.util.Calendar;

/**
 * Created by PC on 05.06.2015.
 */

public class HotelRoomServiceTest extends AbstractDataBaseTest {

    @Autowired
    private HotelRoomDao hotelRoom;

    @Test
    @Rollback(true)
    public void reservationRoomForOneDay() throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.MARCH, 20);


    }
}
