package com.yaroslav.hotel;

import com.yaroslav.hotel.entity.BudgetRoomType;
import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.SizeRoomType;
import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by employee on 6/8/15.
 */
public class Hotel {

    public static final int COUNT_ROOM = 50;

    @Autowired
    private HotelRoomService hotelRoomService;

    public Hotel () {
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

}
