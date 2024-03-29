package com.iau.flight_management.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping
public class PublicController {

    @GetMapping(value = {"/", "/login"})
    public String viewLoginPage() {
        return "/login/loginPage";
    }

    @GetMapping("/register")
    public String viewRegisterPage() {
        return "/login/register";
    }
}
