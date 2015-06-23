package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by PC on 23.06.2015.
 */

@Controller
@RequestMapping(value = "/maid")
public class MaidController {

    @Autowired
    private HotelRoomService hotelRoomService;

    // todo complete this section
    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public String getRoomsForMaid(ModelMap modelMap) {
        modelMap.addAttribute("rooms", hotelRoomService.getMaidRoomsThisDay());
        return "maidRooms";
    }
}
