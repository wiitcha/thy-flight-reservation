package com.iau.flight_management.controller;

import com.iau.flight_management.model.dto.MemberDTO;
import com.iau.flight_management.repository.MemberRepository;
import com.iau.flight_management.security.auth.AuthenticationRequest;
import com.iau.flight_management.service.AuthenticationServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private final MemberRepository memberRepository;
    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/authenticate")
    public String authenticate(HttpServletRequest request,
                               HttpServletResponse response,
                               @RequestBody MultiValueMap<String, String> formData) {

        String token = authenticationService.authenticate(new AuthenticationRequest(
                formData.getFirst("email"),
                formData.getFirst("password")
        ));

        if (token.isEmpty()) {
            return "redirect:/login?error";
        } else {
            /*Cookie cookie = new Cookie("Authorization", token);
            cookie.setPath("/employees");
            response.addCookie(cookie);*/
            request.getSession().setAttribute("Authorization", token);
            return "redirect:/home";
        }

    }

    @PostMapping("/register")
    public String register(@ModelAttribute("member") MemberDTO formData) {
        if (memberRepository.existsByEmail(formData.getEmail())) {
            return "redirect:/register?error";
        } else {
            authenticationService.register(formData);
            return "redirect:/register?success";
        }
    }
}
