package com.iau.flight_management.service;

import com.iau.flight_management.model.entity.Member;
import org.springframework.ui.Model;

public interface CardService {

    String editCard(Long cardId, Member member, Model model);

    String showMyCards(Member member, Model model);

}
