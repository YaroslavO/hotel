package com.yaroslav.hotel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by PC on 21.06.2015.
 */

@Controller
public class UserController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(ModelMap modelMap,
                               @RequestParam(value = "err", required = false) Integer err) {
        if (err != null) {
            modelMap.addAttribute("err", "Not correct login and password");
        }

        return "login";
    }
}
