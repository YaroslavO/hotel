package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by PC on 11.06.2015.
 */

@Controller
public class HotelRoomController {

    @Autowired
    private HotelRoomService hotelRoomService;

    @RequestMapping(value = "/rooms/new", method = RequestMethod.GET)
    public String getCreateNewRoomPage(ModelMap modelMap) {
        modelMap.addAttribute("textHeader", "Add new hotel room in database");
        modelMap.addAttribute("room", new HotelRoom());

        return "addroom";
    }

    @RequestMapping(value = "/rooms", method = RequestMethod.POST)
    public String createNewHotelRoom(@ModelAttribute(value = "room") HotelRoom room) {
        hotelRoomService.addHotelRoom(room);

        return "redirect:/";
    }
}
