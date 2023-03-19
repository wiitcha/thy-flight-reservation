package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Payment;

import java.util.HashMap;

public interface PaymentService {

    double calculateTotal(String[] flights, int passengerCount);
    Payment makePayment(String[] flights, PassengerDTO passengersAndFlightDetails, HashMap<String, String> searchParameters);

}
