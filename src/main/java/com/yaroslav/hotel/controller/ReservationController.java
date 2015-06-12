package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.Period;
import com.yaroslav.hotel.exception.ReservationHotelRoomException;
import com.yaroslav.hotel.service.HotelRoomService;
import com.yaroslav.hotel.service.ReceptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Created by employee on 6/12/15.
 */

@Controller
public class ReservationController {

    @Autowired
    private ReceptionService receptionService;

    @Autowired
    private HotelRoomService hotelRoomService;

    @RequestMapping(value = "/reserve/{id}", method = RequestMethod.POST)
    public String reserveRoomById(@PathVariable("id") Integer idHotelRoom,
                                  @RequestParam("startDate") String startDate,
                                  @RequestParam("endDate") String endDate) {

        Period period = createPeriodFromString(startDate, endDate);

        HotelRoom room = hotelRoomService.getHotelRoomById(idHotelRoom);

        try {
            receptionService.reservation(room, period);
        } catch (ReservationHotelRoomException e) {
            e.printStackTrace();
            return "redirect:/error";
        }

        return "redirect:/";
    }

    private Period createPeriodFromString(String startDate, String endDate) {
        Period period = null;
        DateFormat formatter = new SimpleDateFormat("yy-mm-dd");

        try {
            period = new Period(formatter.parse(startDate), formatter.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return period;
    }
}
