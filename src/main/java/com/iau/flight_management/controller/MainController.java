package com.iau.flight_management.controller;

import org.springframework.stereotype.Controller;
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
