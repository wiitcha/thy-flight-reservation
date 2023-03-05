package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Payment;
import com.iau.flight_management.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

    private Long id;
    private String name;
    private String type;
    private String number;
    private String cvv;
    private String expDate;
    private Member member;
    private List<Payment> payments;
}
