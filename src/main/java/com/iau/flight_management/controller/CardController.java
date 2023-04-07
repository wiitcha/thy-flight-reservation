package com.iau.flight_management.controller;

import com.iau.flight_management.model.dto.CardDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.CardService;
import com.iau.flight_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Controller
@RequestMapping("/cards")
public class CardController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final CardService cardService;
    private static final String SECURITY_LOGOUT = "redirect:/login?logout";

    @GetMapping()
    public String showMyCards(@RequestParam(value = "edit", required = false) Long cardId,
                              HttpServletRequest request,
                              Model model) {

        Optional<Member> member = memberService.extractUser(request);

        if (member.isPresent()) {
            if (cardId != null) {
                return cardService.editCard(cardId, member.get(), model);
            }
            return cardService.showMyCards(member.get(), model);
        }
        return SECURITY_LOGOUT;
    }

    @PostMapping()
    public String addCard(HttpServletRequest request,
                          @ModelAttribute("cardObject") CardDTO cardDTO) {

        String token = request.getSession().getAttribute("Authorization").toString();
        String email = jwtService.extractUsername(token);
        Optional<Member> member = memberService.findByEmail(email);

        if (member.isPresent()) {
            return cardService.saveCard(cardDTO, member.get());
        }

        return SECURITY_LOGOUT;
    }

    @GetMapping("/delete")
    public String deleteCard(HttpServletRequest request,
                             @RequestParam(value = "id") Long cardId) {
        Optional<Member> optionalMember = memberService.extractUser(request);

        if (optionalMember.isPresent()) {
            Member member = optionalMember.get();

            return cardService.deleteCard(cardId, member);
        }
        return SECURITY_LOGOUT;
    }
}
