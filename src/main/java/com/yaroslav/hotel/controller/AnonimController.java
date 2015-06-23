package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by employee on 6/23/15.
 */

@Controller
public class AnonimController {

    @Autowired
    private HotelRoomService hotelRoomService;

    @RequestMapping(name = "/rooms", method = RequestMethod.GET)
    public String getRoomForReservation(ModelMap modelMap) {
        modelMap.addAttribute("rooms", hotelRoomService.getAllRoom());

        return "allRooms";
    }

    @RequestMapping(name = "/reserve/{id}", method = RequestMethod.GET)
    public String getRoomReservationPage(@PathVariable("id") Integer id,
                                         ModelMap modelMap) {

        return "reservation";
    }
}
