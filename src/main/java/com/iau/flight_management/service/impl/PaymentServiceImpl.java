package com.iau.flight_management.service.impl;

import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Payment;
import com.iau.flight_management.repository.PaymentRepository;
import com.iau.flight_management.service.CardService;
import com.iau.flight_management.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService {

    private final CardService cardService;
    private final PaymentRepository paymentRepository;
    @Override
    public BigDecimal calculateTotal(List<FlightDTO> flights, double passengerCount) {
        BigDecimal total = BigDecimal.ZERO;
        for (FlightDTO flight : flights) {
            total = total.add(flight.getPrice());
        }
        total = total.add(total.multiply(BigDecimal.valueOf(0.18)));

        return total.multiply(BigDecimal.valueOf(passengerCount));
    }

    @Override
    public Payment makePayment(List<FlightDTO> flights, Long cardId, List<PassengerDTO> passengers) {
        BigDecimal total = calculateTotal(flights, Double.parseDouble(String.valueOf(passengers.size())));
        Card card = cardService.findCardById(cardId);

        Payment payment = Payment.builder()
                .card(card)
                .total(total)
                .build();

        paymentRepository.save(payment);
        return payment;
    }
}
