package com.iau.flight_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.CardService;
import com.iau.flight_management.service.FlightService;
import com.iau.flight_management.service.MemberService;
import com.iau.flight_management.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RequiredArgsConstructor
@SessionAttributes("Authorization")
@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final JwtService jwtService;
    private final MemberService memberService;
    private final CardService cardService;

    private final ReservationService reservationService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";


    @PostMapping
    public String searchFlights(Model model,
                                @ModelAttribute("Authorization") String token,
                                @RequestBody MultiValueMap<String, String> formData,
                                HttpServletRequest request) throws IOException, InterruptedException {

        String email = jwtService.extractUsername(token);

        if (memberService.existsByEmail(email)) {
            Member member = memberService.findByEmail(email).get();

            List<Card> cards = cardService.findAllCardsOfMember(member);
            model.addAttribute("cards", cards);

            HashMap<String, String> searchParameters= flightService.extractSearchParameters(formData);
            model.addAttribute("flights", flightService.getSearchedFlights(searchParameters));

            model.addAttribute("searchParameters", searchParameters);

            return "home/flights";
        } else {
            return SECURITY_LOGOUT;
        }
    }

    @PostMapping ("/booking")
    public String bookReservation(@ModelAttribute PassengerDTO passengerDTO,
                                  @RequestParam("myMap") String searchParams,
                                  @ModelAttribute("Authorization") String token
                                  ) throws JsonProcessingException {

        String email = jwtService.extractUsername(token);

        if (memberService.existsByEmail(email)) {
            Member member = memberService.findByEmail(email).get();

            return reservationService.bookReservation(member, searchParams, passengerDTO);
        }
        return SECURITY_LOGOUT;
    }
}
