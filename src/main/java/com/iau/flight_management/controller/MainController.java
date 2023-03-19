package com.iau.flight_management.controller;

import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.CardService;
import com.iau.flight_management.service.FlightService;
import com.iau.flight_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
@SessionAttributes("Authorization")
@RequestMapping("/home")
public class MainController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final CardService cardService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";
    private final FlightService flightService;

    @GetMapping
    public String home(Model model) throws IOException, InterruptedException {
        return "home/main";
    }
}
