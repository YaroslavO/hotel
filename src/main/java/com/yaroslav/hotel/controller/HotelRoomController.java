package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.entity.*;
import com.yaroslav.hotel.exception.SearchNullParameterException;
import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by PC on 11.06.2015.
 */

@Controller
@RequestMapping(value = "/rooms")
public class HotelRoomController {
// todo try redirect attributes
    @Autowired
    private HotelRoomService hotelRoomService;

    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public String getCreateNewRoomPage(ModelMap modelMap) {
        modelMap.addAttribute("textHeader", "Add new hotel room in database");
        modelMap.addAttribute("room", new HotelRoom());

        return "addroom";
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createNewHotelRoom(@ModelAttribute(value = "room") HotelRoom room) {
        hotelRoomService.addHotelRoom(room);

        return "redirect:/";
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getRooms(ModelMap modelMap) {
        modelMap.addAttribute("textHeader", "Show all rooms");
        modelMap.addAttribute("rooms", hotelRoomService.getAllRoom());
        modelMap.addAttribute("searchForm", new SearchFormEntity());

        return "showrooms";
    }

    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public String getSearchPage(ModelMap modelMap) {
        modelMap.addAttribute("searchForm", new SearchFormEntity());
        modelMap.addAttribute("textHeader", "Show search room");

        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String searchRooms(ModelMap modelMap,
                              @ModelAttribute(value = "searchForm") SearchFormEntity searchForm) {
        modelMap.addAttribute("textHeader", "Show search room");
        modelMap.addAttribute("searchForm", new SearchFormEntity());

        try {
            HqlQueryHotelRoomSearchBuilder hqlBuilder = searchForm.createHQLBuilderForThisFormEntity();
            modelMap.addAttribute("rooms", hotelRoomService.searchHotelRoomByParameter(hqlBuilder));
        } catch (SearchNullParameterException e) {
            e.printStackTrace();
        }

        return "search";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public String deleteRoom(@PathVariable("id") Integer id) {
        HotelRoom room = hotelRoomService.getHotelRoomById(id);
        hotelRoomService.delete(room);

        return "redirect:/";
    }

}
