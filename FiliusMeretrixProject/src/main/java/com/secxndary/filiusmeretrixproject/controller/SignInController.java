package com.secxndary.filiusmeretrixproject.controller;
import com.secxndary.filiusmeretrixproject.dto.UserDto;
import com.secxndary.filiusmeretrixproject.dto.UserLoginDto;
import com.secxndary.filiusmeretrixproject.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/login")
public class SignInController {

    private final UserService userService;


    @GetMapping
    public ModelAndView showLoginPage(Model model)
    {
        ModelAndView modelAndView = new ModelAndView("login");
        log.info("/login was called GET");
        return modelAndView;
    }


    @PostMapping
    @ResponseStatus(value = HttpStatus.OK)
    public ResponseEntity login(@RequestBody @Valid UserLoginDto userLoginDto) {
        log.info("/login was called POST");
        Map<Object, Object> response = new HashMap<>();
        return ResponseEntity.ok(response);
    }
}
