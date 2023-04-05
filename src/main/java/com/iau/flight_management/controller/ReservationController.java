package com.iau.flight_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.model.FlightReservationModel;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.model.entity.Reservation;
import com.iau.flight_management.service.MemberService;
import com.iau.flight_management.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/reservations")
public class ReservationController {
    private final MemberService memberService;
    private final ReservationService reservationService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";

    @GetMapping
    public String showReservations(Model model, HttpServletRequest request) {
        Optional<Member> member = memberService.extractUser(request);

        if (member.isPresent()) {
            Member loggedInMember = member.get();

            List<Reservation> reservations = reservationService.findAllReservationsOfMember(loggedInMember);
            model.addAttribute("reservations", reservationService.convertReservationsToDto(reservations));
        }
        return "home/myFlights";
    }

    @PostMapping("/booking")
    public String bookReservation(@RequestBody FlightReservationModel flightReservationModel, HttpServletRequest request
    ) throws JsonProcessingException {
        Optional<Member> memberOptional = memberService.extractUser(request);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();

            return reservationService.bookReservation(member, flightReservationModel);
        }
        return SECURITY_LOGOUT;
    }
}
