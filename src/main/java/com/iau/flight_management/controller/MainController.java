package com.iau.flight_management.controller;

import com.iau.flight_management.model.dto.CardDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.CardRepository;
import com.iau.flight_management.repository.MemberRepository;
import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.CardService;
import com.iau.flight_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@SessionAttributes("Authorization")
@RequestMapping("/home")
public class MainController {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    private final MemberService memberService;
    private final CardService cardService;

    private final CardRepository cardRepository;


    @GetMapping
    public String home() {
        return "home/main";
    }

    @GetMapping("/cards")
    public String showMyCards(@RequestParam(value = "edit", required = false) Long cardId,
                            @ModelAttribute("Authorization") String token,
                            Model model,
                            HttpServletRequest request) {

        String email = jwtService.extractUsername(token);

        if (memberService.existsByEmail(email)) {
            Member member = memberService.findByEmail(email).get();

            if (cardId != null) {
                return cardService.editCard(cardId, member, model);
            }

            return cardService.showMyCards(member, model);
        }

        return "redirect:/home?logout"; // for security
    }

    @PostMapping("/cards")
    public String addCard(@ModelAttribute("Authorization") String token,
            @ModelAttribute("cardObject") CardDTO cardDTO,
                          HttpServletRequest request) {

        //String token = request.getSession().getAttribute("Authorization").toString();
        String email = jwtService.extractUsername(token);

        if (memberRepository.existsByEmail(email)) {

            Member member = memberRepository.findByEmail(email).get();

            List<Card> cards = cardRepository.findAllByMemberIs(member);

            if (!cards.isEmpty()) {
                for (Card card : cards) {
                    if (card.getNumber().equals(cardDTO.getNumber())) { // check if the user is trying to save the same card
                        return "redirect:/home/cards?error";
                    }
                }
            } else {
                Card card = Card.builder()
                        .name(cardDTO.getName())
                        .type(cardDTO.getType())
                        .number(cardDTO.getNumber())
                        .cardHolder(cardDTO.getCardHolder())
                        .cvv(cardDTO.getCvv())
                        .expDate(cardDTO.getExpDate())
                        .member(member)
                        .build();

                cardRepository.save(card);
                return "redirect:/home/cards?success";
            }
        }
        return "redirect:/home?logout"; // for security
    }

    /*@PostMapping(@ModelAttribute("Authorization") String token)
    public String deleteCard() {

    }*/

    @GetMapping("/flights")
    public String showMyFlight() {
        return "home/myFlights";
    }
}
