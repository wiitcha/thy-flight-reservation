package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {

    private Long id;
    private double total;
    private Card card;
    private Reservation reservation;
}
