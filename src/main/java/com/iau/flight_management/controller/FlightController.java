package com.iau.flight_management.controller;

import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
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
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/flights")
public class FlightController {

    private final FlightService flightService;
    private final MemberService memberService;
    private final CardService cardService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";

    @PostMapping
    public String searchFlights(Model model,
                                @RequestBody MultiValueMap<String, String> formData,
                                HttpServletRequest request) throws IOException, InterruptedException, ParseException {

        Optional<Member> optionalMember = memberService.extractUser(request);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

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
