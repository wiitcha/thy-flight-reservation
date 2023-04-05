package com.iau.flight_management.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
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
    private MemberDTO member;
    private List<PaymentDTO> payments;
}
