package com.iau.flight_management.controller;

import com.iau.flight_management.model.dto.CardDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.CardRepository;
import com.iau.flight_management.repository.MemberRepository;
import com.iau.flight_management.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/home")
public class MainController {

    private final JwtService jwtService;
    private final MemberRepository memberRepository;

    private final CardRepository cardRepository;


    @GetMapping
    public String home() {
        return "home/main";
    }

    @GetMapping("/cards")
    public String showMyCards(Model model,
            HttpServletRequest request) {
        boolean hasAnyCard;
        String token = request.getSession().getAttribute("Authorization").toString();
        String email = jwtService.extractUsername(token);

        if (memberRepository.existsByEmail(email)) {
            Member member = memberRepository.findByEmail(email).get();

            if (member.getCards().isEmpty()) {
                hasAnyCard = false;
            } else {
                hasAnyCard= true;
                model.addAttribute("cards", member.getCards());
            }

            model.addAttribute("hasAnyCard", hasAnyCard);

            return "home/myCards";
        }

        return "redirect:/home?logout"; // for security
    }

    @PostMapping("/cards")
    public String addCard(@ModelAttribute("card") CardDTO cardDTO,
                          HttpServletRequest request) {

        String token = request.getSession().getAttribute("Authorization").toString();
        String email = jwtService.extractUsername(token);

        if (memberRepository.existsByEmail(email)) {

            Member member = memberRepository.findByEmail(email).get();

            List<Card> cards = cardRepository.findAllByMemberIs(member);

            if (!cards.isEmpty()) {
                for (Card card : cards) {
                    if (card.getNumber().equals(cardDTO.getNumber())) {
                        return "redirect:/home/cards?error";
                    }
                }
            }

            Card card = Card.builder()
                    .name(cardDTO.getName())
                    .type(cardDTO.getType())
                    .number(cardDTO.getNumber())
                    .cvv(cardDTO.getCvv())
                    .expDate(cardDTO.getExpDate())
                    .member(member).build();

            cardRepository.save(card);

            return "redirect:/home/cards?success";
        }
        return "redirect:/home?logout"; // for security
    }

    @GetMapping("/flights")
    public String showMyFlight() {
        return "home/myFlights";
    }
}
