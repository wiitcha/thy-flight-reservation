package com.iau.flight_management.controller;

import com.iau.flight_management.model.dto.MemberDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final MemberService memberService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";

    @GetMapping
    public String showProfile(Model model, HttpServletRequest request) {

        Optional<Member> member = memberService.extractUser(request);

        if (member.isPresent()) {
            Member loggedInMember = member.get();

            model.addAttribute("member", memberService.convertMemberToDto(loggedInMember));
        }

        return "home/editProfile";
    }

    @PostMapping("/preferredAddress")
    public String updatePreferredAddress(MemberDTO memberDTO, HttpServletRequest request) {

        Optional<Member> member = memberService.extractUser(request);

        if (member.isPresent()) {
            Member loggedInMember = member.get();

            loggedInMember.setAddress(memberDTO.getAddress());
            loggedInMember.setCountry(memberDTO.getCountry());
            loggedInMember.setCity(memberDTO.getCity());
            loggedInMember.setPhoneNumber(memberDTO.getPhoneNumber().replace(",", ""));
            memberService.saveMember(loggedInMember);

            return "redirect:/profile";
        }

        return SECURITY_LOGOUT;
    }


}
