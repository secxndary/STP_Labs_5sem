package com.secxndary.stp_labs2_8.controller;
import com.secxndary.stp_labs2_8.entity.Users;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@Controller
public class LoginController {
    private static List<Users> usersList = new ArrayList<Users>();

    @Value("${user.login}")
    private String login;

    @Value("${user.password}")
    private String password;



    @GetMapping(value = {"/register"})          // маршрутизация
    public ModelAndView registerUser(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("register");
        var user = new Users();
        modelAndView.setViewName("register");      // файл
        model.addAttribute("user", user);
        log.info("/register was called GET");
        return modelAndView;
    }
}
