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
@RequestMapping("/home")
public class MainController {

    @GetMapping
    public String home() throws IOException, InterruptedException {
        return "home/main";
    }
}
