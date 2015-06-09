package com.yaroslav.hotel;

import com.yaroslav.hotel.entity.*;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;
import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Created by employee on 6/8/15.
 */
public class Hotel {

    public static final int COUNT_ROOM = 20;

    private HotelRoomService hotelRoomService;

    public Hotel() {

    }

    @Autowired
    public Hotel(HotelRoomService hotelRoomService) {
        this.hotelRoomService = hotelRoomService;
        init();
    }

    public void init() {
        for (int numberRoom = 1; numberRoom <= COUNT_ROOM; numberRoom++) {
            HotelRoom room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.STANDARD);
            hotelRoomService.addHotelRoom(room);

            room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.STANDARD);
            hotelRoomService.addHotelRoom(room);

            room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.LUX);
            hotelRoomService.addHotelRoom(room);

            room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.LUX);
            hotelRoomService.addHotelRoom(room);

            room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
            hotelRoomService.addHotelRoom(room);

            room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);
            hotelRoomService.addHotelRoom(room);

            room = new HotelRoom(SizeRoomType.SGL, BudgetRoomType.ECONOM);
            hotelRoomService.addHotelRoom(room);

            room = new HotelRoom(SizeRoomType.DBL, BudgetRoomType.ECONOM);
            hotelRoomService.addHotelRoom(room);
        }
    }

    public String getAllHotelRoom() {
        return hotelRoomService
                .getAllRoom()
                .stream()
                .map(p -> p.toString())
                .collect(Collectors.joining("\n"));
    }

    public String someWork() {
        HotelRoom hotelRoom = hotelRoomService.getHotelRoomById(5);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 5);
        Date date = calendar.getTime();
        calendar.set(Calendar.DAY_OF_YEAR, 4);
        Date startDate = calendar.getTime();
        calendar.set(Calendar.DAY_OF_YEAR, 6);
        Date endDate = calendar.getTime();

        Period period = new Period(startDate, endDate);

        try {
            hotelRoomService.reservation(hotelRoom, date);
        } catch (ReservationHotelRoomException e) {
            e.printStackTrace();
        }

        Parameter parameter = new Parameter();
        parameter.period = period;
        parameter.countHotelRoom = 10;
//        parameter.setNotThisBudgetRoomType(BudgetRoomType.ECONOM);
//        parameter.sizeRoomType = SizeRoomType.DBL;
        List<HotelRoom> rooms = hotelRoomService.searchHotelRoomByParameter(parameter);
        return rooms
                .stream()
                .map(p -> p.toString())
                .collect(Collectors.joining("\n"));
    }
}
