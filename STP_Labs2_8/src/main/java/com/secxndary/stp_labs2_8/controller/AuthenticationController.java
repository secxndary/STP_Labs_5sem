package com.secxndary.stp_labs2_8.controller;
import com.secxndary.stp_labs2_8.dto.UserDto;
import com.secxndary.stp_labs2_8.entity.User;
import com.secxndary.stp_labs2_8.mapper.UserMapper;
import com.secxndary.stp_labs2_8.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

// TODO: request mapping, rest documentation
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/register")
//@RequestMapping(value = "api/auth/")
public class AuthenticationController {
    private static List<User> usersList = new ArrayList<User>();
    private final UserService userService;

    // TODO: удалить нахуй
    @Value("${user.login}")
    private String login;

    @Value("${user.password}")
    private String password;


    @GetMapping()          // маршрутизация
    public ModelAndView showRegisterPage(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("register");
        var user = new User();
//        modelAndView.setViewName("register");      // файл
        model.addAttribute("user", user);
        log.info("/register was called GET");
        return modelAndView;
    }




    @PostMapping
//    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public void register(@Valid UserDto user) {
        userService.register(user);
    }


//
//    @PostMapping(value = {"/register"})
//    public ModelAndView saveAlbum(Model model, @ModelAttribute("user") User user)
//    {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("register");
//        String username = user.getUsername();
//        String email = user.getEmail();
//        if (username != null && username.length() > 0 && email != null && email.length() > 0)
//        {
//            User userToRegister = new User(username, password);
//            usersList.add(userToRegister);
//            System.out.println(userToRegister.getUsername());
//        }
//        log.info("/register was called POST");
//        return modelAndView;
//    }



}
