package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.CardDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Optional;

public interface CardService {

    String editCard(Long cardId, Member member, Model model);
    String showMyCards(Member member, Model model);
    List<Card> findAllCardsOfMember(Member member);
    String deleteCard(Long cardId, Member member);
    String saveCard(CardDTO cardDTO, Member member);
    Card findCardById(Long id);
    boolean checkDuplicateCards(CardDTO cardDTO, Member member);

}
