package com.iau.flight_management.controller;

import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.CardService;
import com.iau.flight_management.service.FlightService;
import com.iau.flight_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@RequiredArgsConstructor
@SessionAttributes("Authorization")
@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final JwtService jwtService;
    private final MemberService memberService;
    private final CardService cardService;
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

            HashMap<String, String> searchParameters= flightService.extractSearchParameters(formData, model);
            model.addAttribute("searchParameters", searchParameters);

            return "home/flights";
        } else {
            return SECURITY_LOGOUT;
        }
    }


}
