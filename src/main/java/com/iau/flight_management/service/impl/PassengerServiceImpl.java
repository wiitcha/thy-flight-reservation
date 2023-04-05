package com.iau.flight_management.service.impl;

import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Passenger;
import com.iau.flight_management.repository.PassengerRepository;
import com.iau.flight_management.service.PassengerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class PassengerServiceImpl implements PassengerService {

    private final PassengerRepository passengerRepository;

    @Override
    public List<Passenger> savePassengers(List<PassengerDTO> passengerDTOS) {

        List<Passenger> passengers = new ArrayList<>();

        for (PassengerDTO passengerDTO : passengerDTOS) {
            Passenger passenger = Passenger.builder()
                    .name(passengerDTO.getName())
                    .surname(passengerDTO.getSurname())
                    .dateOfBirth(passengerDTO.getDateOfBirth())
                    .gender(passengerDTO.getGender())
                    .phoneNumber(passengerDTO.getPhoneNumber())
                    .build();

            passengerRepository.save(passenger);
            passengers.add(passenger);
        }

        return passengers;
    }
}
