package com.secxndary.filiusmeretrixproject.controller;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@Slf4j
@RestController
@RequiredArgsConstructor
public class HomePageController {

    @GetMapping("/homeAdmin")
    public ModelAndView showHomeAdmin(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("homeAdmin");
        log.info("/homeAdmin was called GET");
        return modelAndView;
    }


    @GetMapping("/homeUser")
    public ModelAndView showHomeUser(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("homeUser");
        log.info("/homeUser was called GET");
        return modelAndView;
    }
}
