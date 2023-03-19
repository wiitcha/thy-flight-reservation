package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Payment;
import com.iau.flight_management.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class PaymentServiceImpl implements PaymentService{

    private final CardService cardService;
    private final PaymentRepository paymentRepository;
    @Override
    public double calculateTotal(String[] flights, int passengerCount) {
        double total = 0;

        for (int i = 0; i < flights.length; i++) {
            String[] flight = flights[i].split(",");
            total += Double.parseDouble(flight[flight.length - 1]);
        }
        return total * passengerCount;
    }

    @Override
    public Payment makePayment(String[] flights, PassengerDTO passengersAndFlightDetails, HashMap<String, String> searchParameters) {

        Card card = cardService.findCardById(Long.valueOf(passengersAndFlightDetails.getCardId()));
        double total = calculateTotal(flights, Integer.parseInt(searchParameters.get("passengers")));

        Payment payment = Payment.builder()
                .card(card)
                .total(total)
                .build();

        paymentRepository.save(payment);
        return payment;
    }
}
