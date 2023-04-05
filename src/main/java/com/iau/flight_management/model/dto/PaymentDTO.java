package com.iau.flight_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDTO {
    private Long id;
    private BigDecimal total;
    private CardDTO card;
    private ReservationDTO reservation;

    public PaymentDTO(Long id, BigDecimal total, CardDTO card) {
        this.id = id;
        this.total = total;
        this.card = card;
    }
}
