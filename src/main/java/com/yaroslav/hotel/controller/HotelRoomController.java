package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.entity.HotelRoom;
import com.yaroslav.hotel.entity.HqlQueryHotelRoomSearchBuilder;
import com.yaroslav.hotel.entity.Period;
import com.yaroslav.hotel.exception.SearchNullParameterException;
import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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

    @RequestMapping(value = "/rooms", method = RequestMethod.GET)
    public String getRooms(ModelMap modelMap) {
        modelMap.addAttribute("textHeader", "Show all rooms page");
        modelMap.addAttribute("rooms", hotelRoomService.getAllRoom());

        return "showrooms";
    }

    @RequestMapping(value = "/rooms/search", method = RequestMethod.POST)
    public String searchRooms(ModelMap modelMap,
                              @RequestParam("startDate") String startDate,
                              @RequestParam("endDate") String endDate) {

        Period period = createPeriodFromString(startDate, endDate);
        HqlQueryHotelRoomSearchBuilder hqlBuilder = new HqlQueryHotelRoomSearchBuilder();
        hqlBuilder.setPeriod(period);

        modelMap.addAttribute("textHeader", "Show search room");

        try {
            modelMap.addAttribute("rooms", hotelRoomService.searchHotelRoomByParameter(hqlBuilder));
        } catch (SearchNullParameterException e) {
            e.printStackTrace();
        }

        return "showrooms";
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
