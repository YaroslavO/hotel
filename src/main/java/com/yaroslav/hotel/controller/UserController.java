package com.yaroslav.hotel.controller;

import com.yaroslav.hotel.entity.User;
import com.yaroslav.hotel.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

/**
 * Created by PC on 21.06.2015.
 */

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String getLoginPage(ModelMap modelMap,
                               @RequestParam(value = "err", required = false) Integer err) {
        if (err != null) {
            modelMap.addAttribute("err", "Not correct login and password");
        }

        return "login";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.GET)
    public String register(ModelMap model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @RequestMapping(value = "/admin/registration", method = RequestMethod.POST)
    public String addNewUser(@ModelAttribute("user") User user,
                             @RequestParam("role") String role) {
        role = "ROLE_" + role;
        userService.save(user, role);

        return "redirect:/";
    }
}
