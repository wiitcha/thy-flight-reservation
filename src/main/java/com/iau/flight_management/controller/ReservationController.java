package com.iau.flight_management.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.mapper.ReservationMapper;
import com.iau.flight_management.model.FlightReservationModel;
import com.iau.flight_management.model.dto.ReservationDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.model.entity.Reservation;
import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.MemberService;
import com.iau.flight_management.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@SessionAttributes("Authorization")
@Controller
@RequestMapping("/reservations")
public class ReservationController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final ReservationService reservationService;

    private final ReservationMapper reservationMapper;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";

    @GetMapping
    public String showReservations(Model model, HttpServletRequest request) {

        Optional<Member> member = memberService.extractUser(request);

        if (member.isPresent()) {
            Member loggedInMember = member.get();

            List<Reservation> reservations = reservationService.findAllReservationsOfMember(loggedInMember);
//                    .stream().map(EntityMapper.INSTANCE::map).collect(Collectors.toList());

            List<ReservationDTO> reservationDTOS = new ArrayList<>();

            for (Reservation reservation: reservations) {
                reservationDTOS.add(reservationMapper.toDto(reservation));
            }

            model.addAttribute("reservations", reservationDTOS);
           // model.addAttribute("reservationDTOS", reservationDTOS);
        }
        return "home/myFlights";
    }

    public ReservationDTO reservationToDto(Reservation value) {
        if ( value == null ) {
            return null;
        }
        ReservationDTO reservationDTO = new ReservationDTO();

        if ( value.getReservationType() != null ) {
            reservationDTO.setReservationType( value.getReservationType() );
        }
        if ( value.getId() != null ) {
            reservationDTO.setId( value.getId() );
        }
        if ( value.getDate() != null ) {
            reservationDTO.setDate( value.getDate() );
        }
        if ( value.getFlights() != null ) {
//            reservationDTO.setDate( value.getDate() );
        }

        return reservationDTO;
    }

    @PostMapping("/booking")
    public String bookReservation(@RequestBody FlightReservationModel flightReservationModel,
                                  @ModelAttribute("Authorization") String token
    ) throws JsonProcessingException {

        String email = jwtService.extractUsername(token);

        Optional<Member> memberOptional = memberService.findByEmail(email);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            //Passenger passenger = EntityMapper.INSTANCE.map(passengerDTO);
            return reservationService.bookReservation(member, flightReservationModel);
        }
        return SECURITY_LOGOUT;
    }
}
