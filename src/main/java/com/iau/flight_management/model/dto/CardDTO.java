package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Payment;
import com.iau.flight_management.model.entity.Member;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

    private Long id;
    private String name;
    private String type;
    private String number;
    private String cardHolder;
    private String cvv;
    private String expDate;
    private Member member;
    private List<Payment> payments;
}
