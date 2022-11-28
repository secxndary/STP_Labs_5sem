package com.secxndary.stp_labs2_8.controller;
import com.secxndary.stp_labs2_8.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
public class AuthenticationController {
    private static List<User> usersList = new ArrayList<User>();

    @Value("${user.login}")
    private String login;

    @Value("${user.password}")
    private String password;


    @GetMapping(value = {"/register"})          // маршрутизация
    public ModelAndView showRegisterPage(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("register");
        var user = new User();
        modelAndView.setViewName("register");      // файл
        model.addAttribute("user", user);
        log.info("/register was called GET");
        return modelAndView;
    }


    @PostMapping(value = {"/register"})
    public ModelAndView saveAlbum(Model model, @ModelAttribute("user") User user)
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        String username = user.getUsername();
        String email = user.getEmail();
        if (username != null && username.length() > 0 && email != null && email.length() > 0)
        {
            User userToRegister = new User(username, password);
            usersList.add(userToRegister);
            System.out.println(userToRegister.getUsername());
        }
        log.info("/addalbum was called POST");
        return modelAndView;
    }
}
