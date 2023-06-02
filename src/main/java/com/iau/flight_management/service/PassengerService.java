package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Passenger;

import java.util.List;

public interface PassengerService {

    List<Passenger> savePassengers(List<PassengerDTO> passengerDTOS);
    Passenger updatePassenger(PassengerDTO passengerDTO);
}
