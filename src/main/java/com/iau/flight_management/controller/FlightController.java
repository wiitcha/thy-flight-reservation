package com.iau.flight_management.controller;

import com.iau.flight_management.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;


@RequiredArgsConstructor
@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;



}
