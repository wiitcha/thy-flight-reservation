package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Passenger;
import org.springframework.stereotype.Component;

@Component
public class PassengerMapper {

    public PassengerDTO toDto(Passenger passenger) {
        return new PassengerDTO(passenger.getId(), passenger.getName(),
                passenger.getSurname(), passenger.getDateOfBirth(), passenger.getGender(), passenger.getPhoneNumber());

    }
}
