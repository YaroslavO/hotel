package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.entity.User;
import com.yaroslav.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by PC on 11.06.2015.
 */

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage(ModelMap modelMap) {
        modelMap.addAttribute("textHeader", "Welcome to hotel main page");
        User user = userService.getUserByLogin("root");

        return "index";
    }
}
