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

@RequiredArgsConstructor
@SessionAttributes("Authorization")
@Controller
@RequestMapping("/cards")
public class CardController {

    private final JwtService jwtService;
    private final MemberService memberService;
    private final CardService cardService;
    private static final String SECURITY_LOGOUT = "redirect:/home?logout";

    @GetMapping()
    public String showMyCards(@RequestParam(value = "edit", required = false) Long cardId,
                              @ModelAttribute("Authorization") String token,
                              Model model) {

        String email = jwtService.extractUsername(token);

        if (memberService.existsByEmail(email)) {
            Member member = memberService.findByEmail(email).get();

            if (cardId != null) {
                return cardService.editCard(cardId, member, model);
            }

            return cardService.showMyCards(member, model);
        }

        return SECURITY_LOGOUT; // for security
    }

    @PostMapping()
    public String addCard(@ModelAttribute("Authorization") String token,
                          @ModelAttribute("cardObject") CardDTO cardDTO) {

        String email = jwtService.extractUsername(token);

        if (memberService.existsByEmail(email)) {
            Member member = memberService.findByEmail(email).get();

            return cardService.saveCard(cardDTO, member);
        }
        return SECURITY_LOGOUT;
    }

    @GetMapping("/delete")
    public String deleteCard(@ModelAttribute("Authorization") String token,
                             @RequestParam(value = "id") Long cardId) {

        String email = jwtService.extractUsername(token);

        if (memberService.existsByEmail(email)) {
            Member member = memberService.findByEmail(email).get();

            return cardService.deleteCard(cardId, member);
        }
        return SECURITY_LOGOUT;
    }
}
