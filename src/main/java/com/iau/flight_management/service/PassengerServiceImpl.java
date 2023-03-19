package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Passenger;
import com.iau.flight_management.repository.PassengerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService{

    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> savePassengers(PassengerDTO passengers) {

        List<Passenger> passengerList = new ArrayList<>();

        for (int i = 0; i < passengers.getName().length; i++) {
            Passenger passenger = Passenger.builder()
                    .name(passengers.getName()[i])
                    .surname(passengers.getSurname()[i])
                    .dateOfBirth(passengers.getDateOfBirth()[i])
                    .gender(passengers.getGender()[i])
                    .phoneNumber(passengers.getPhoneNumber()[i])
                    .build();

            passengerRepository.save(passenger);
            passengerList.add(passenger);
        }
        return passengerList;
    }
}
