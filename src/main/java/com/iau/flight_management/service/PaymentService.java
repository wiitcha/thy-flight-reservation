package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.*;

import java.math.BigDecimal;
import java.util.List;

public interface PaymentService {

    BigDecimal calculateTotal(List<FlightDTO> flights, double passengerCount);
    Payment makePayment(List<FlightDTO> flights, Long cardId, List<PassengerDTO> passengerDTOS);

}
