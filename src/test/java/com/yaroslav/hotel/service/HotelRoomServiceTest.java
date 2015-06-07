package com.yaroslav.hotel.service;

import com.yaroslav.hotel.AbstractDataBaseTest;
import com.yaroslav.hotel.dao.HotelRoomDao;
import com.yaroslav.hotel.entity.ClassHotelRoom;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.TypeHotelRoom;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
/**
 * Created by PC on 05.06.2015.
 */

public class HotelRoomServiceTest extends AbstractDataBaseTest {

    @Mock
    private HotelRoomDao hotelRoomDao;

    @InjectMocks
    @Autowired
    private HotelRoomService hotelRoomService;

    private Calendar calendar;
    private HotelRoom room;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        calendar = Calendar.getInstance();
        calendar.set(2015, Calendar.MARCH, 20);
        room = new HotelRoom();
        room.setType(TypeHotelRoom.SGL);
        room.setClassRoom(ClassHotelRoom.ECONOM);
    }

    @Test
    public void reservationHotelRoomForOneDay() throws Exception {

    }

    // TODO try with mock
    @Test
    public void reservationHotelRoomForSomeCountDay() throws Exception {


    }
}
