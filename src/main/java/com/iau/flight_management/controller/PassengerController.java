package com.iau.flight_management.controller;

import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.service.MemberService;
import com.iau.flight_management.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/passenger")
public class PassengerController {

    private final PassengerService passengerService;
    private final MemberService memberService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";

    @PostMapping("/update")
    public String updatePassenger(@ModelAttribute PassengerDTO passengerDTO, HttpServletRequest request) {
        Optional<Member> memberOptional = memberService.extractUser(request);

        if (memberOptional.isPresent()) {
            passengerService.updatePassenger(passengerDTO);

            return "redirect:/reservations";
        }
        return SECURITY_LOGOUT;
    }


}
