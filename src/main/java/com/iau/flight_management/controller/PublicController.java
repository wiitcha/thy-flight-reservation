package com.iau.flight_management.controller;

import com.iau.flight_management.service.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;


@RequiredArgsConstructor
@Controller
@RequestMapping
public class PublicController {

    private final CountryService countryService;

    @GetMapping(value = {"/", "/login"})
    public String viewLoginPage() {
        return "login/loginPage";
    }

    @GetMapping("/register")
    public String viewRegisterPage(Model model) throws IOException, InterruptedException {
        countryService.importCountries(model);

        return "/login/register";
    }
}
