package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.entity.*;
import com.yaroslav.hotel.exception.SearchNullParameterException;
import com.yaroslav.hotel.resources.HotelRoomResources;
import com.yaroslav.hotel.service.HotelRoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by PC on 11.06.2015.
 */

@Controller
@RequestMapping(value = "/admin/rooms")
public class AdminController {

    @Autowired
    private HotelRoomService hotelRoomService;

    // i don't know use it or not use
    @RequestMapping(value = "/new", method = RequestMethod.GET)
    public ResponseEntity<HotelRoom> getCreateNewRoomPage() {

        return new ResponseEntity<HotelRoom>(new HotelRoom(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public String createNewHotelRoom(@ModelAttribute(value = "room") HotelRoom room) {
        hotelRoomService.addHotelRoom(room);

        return "redirect:/";
    }

    //it's rigth method
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<HotelRoomResources> getRooms() {
        List<HotelRoom> rooms = hotelRoomService.getAllRoom();
        HotelRoomResources hotelRoomResources = new HotelRoomResources();
        hotelRoomResources.setRooms(rooms);

        if (rooms != null) {
            return new ResponseEntity<>(hotelRoomResources, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
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
