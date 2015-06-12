package com.yaroslav.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by PC on 11.06.2015.
 */

@Controller
public class IndexController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndexPage(ModelMap modelMap) {
        modelMap.addAttribute("textHeader", "Welcome to hotel main page");
        return "index";
    }
}
