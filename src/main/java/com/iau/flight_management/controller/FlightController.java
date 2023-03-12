package com.iau.flight_management.controller;

import com.iau.flight_management.service.FlightService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;


@RequiredArgsConstructor
@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;

    @PostMapping
    public String searchFlights(Model model,
                                @RequestBody MultiValueMap<String, String> formData,
                                HttpServletRequest request) throws IOException, InterruptedException {

        HashMap<String, String> searchParameters= flightService.extractSearchParameters(formData);

        model.addAttribute("flights", flightService.getSearchedFlights(searchParameters));
        model.addAttribute("searchParameters", searchParameters);

        request.getSession().setAttribute("Flight_Details", searchParameters);


        //model.addAttribute("flightSearchAPIToken", flightService.generateFlightSearchAPIToken(searchParameters));

        return "home/flights";
    }



}
