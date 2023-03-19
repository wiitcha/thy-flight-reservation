package com.iau.flight_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.MemberService;
import com.iau.flight_management.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@SessionAttributes("Authorization")
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final ReservationService reservationService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";

    @GetMapping
    public String showReservations() {
        return "home/myFlights";
    }
    @PostMapping("/booking")
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
