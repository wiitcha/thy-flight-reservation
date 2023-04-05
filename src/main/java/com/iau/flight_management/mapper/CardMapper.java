package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.CardDTO;
import com.iau.flight_management.model.entity.Card;
import org.springframework.stereotype.Component;

@Component
public class CardMapper {

    public CardDTO toDto(Card card) {
        return new CardDTO(card.getId(),card.getName(),card.getType(), card.getNumber(), card.getCardHolder(),
                card.getCvv(), card.getExpDate());
    }
}
