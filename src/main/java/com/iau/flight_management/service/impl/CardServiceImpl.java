package com.iau.flight_management.service.impl;

import com.iau.flight_management.model.dto.CardDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.CardRepository;
import com.iau.flight_management.service.CardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {

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

    @Override
    public List<Card> findAllCardsOfMember(Member member) {
        return cardRepository.findAllByMemberIs(member);
    }

    @Override
    public String deleteCard(Long cardId, Member member) {
        if (cardRepository.existsCardByIdAndMemberIs(cardId, member)) {
            Card card = cardRepository.findById(cardId).get();
            cardRepository.delete(card);

            return "redirect:/cards?deleteSuccess";
        }
        return "redirect:/cards?deleteError";
    }

    @Override
    public String saveCard(CardDTO cardDTO, Member member) {

        if (!checkDuplicateCards(cardDTO, member)) {
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

            return "redirect:/cards";
        } else {
            return "redirect:/cards?duplicate";
        }
    }

    @Override
    public Card findCardById(Long id) {
        return cardRepository.findById(id).get();
    }

    @Override
    public boolean checkDuplicateCards(CardDTO cardDTO, Member member) {
        List<Card> cards = cardRepository.findAllByMemberIs(member);

        if (!cards.isEmpty()) {
            for (Card card : cards) {
                if (card.getNumber().equals(cardDTO.getNumber())) { // check if the user is trying to save the same card
                    return true;
                }
            }
        }
        return false;
    }
}
