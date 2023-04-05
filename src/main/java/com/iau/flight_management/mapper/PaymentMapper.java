package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.PaymentDTO;
import com.iau.flight_management.model.entity.Payment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PaymentMapper {

    private final CardMapper cardMapper;

    public PaymentDTO toDto(Payment payment) {
        return new PaymentDTO(payment.getId(), payment.getTotal(), cardMapper.toDto(payment.getCard()));
    }
}
