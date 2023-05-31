package com.iau.flight_management.controller;

import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/profile")
public class ProfileController {

    private final MemberService memberService;

    @GetMapping
    public String showProfile(Model model, HttpServletRequest request) {

        Optional<Member> member = memberService.extractUser(request);

        if (member.isPresent()) {
            Member loggedInMember = member.get();

            model.addAttribute("member", memberService.convertMemberToDto(loggedInMember));
        }

        return "home/editProfile";
    }


}
