package com.iau.flight_management.service;

import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService{

    private final CardRepository cardRepository;

    @Override
    public String editCard(Long cardId, Member member, Model model) {
        if(cardRepository.existsCardByIdAndMemberIs(cardId, member)) {
            Optional<Card> card = cardRepository.findById(cardId);

            model.addAttribute("cardEdit", card.get());
            return "home/myCards";
        }
        return "home/main";
    }

    @Override
    public String showMyCards(Member member, Model model) {
        boolean hasAnyCard;

        if (member.getCards().isEmpty()) {
            hasAnyCard = false;
        } else {
            hasAnyCard= true;
            model.addAttribute("cards", member.getCards());
        }

        model.addAttribute("hasAnyCard", hasAnyCard);

        return "home/myCards";
    }
}
