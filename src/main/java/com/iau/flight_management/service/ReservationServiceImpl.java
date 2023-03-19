package com.iau.flight_management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.*;
import com.iau.flight_management.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService{

    private final ReservationRepository reservationRepository;
    private final CardService cardService;
    private final PaymentService paymentService;
    private final PassengerService passengerService;
    private final FlightService flightService;

    @Override
    public String bookReservation(Member member, String searchParams, PassengerDTO passengersAndFlightDetails) throws JsonProcessingException {
        HashMap<String, String> searchParameters = extractSearchParams(searchParams);
        String[] flights = passengersAndFlightDetails.getFlightDetails().split("/");

        Payment payment = paymentService.makePayment(flights, passengersAndFlightDetails, searchParameters);
        List<Passenger> passengerList = passengerService.savePassengers(passengersAndFlightDetails);
        List<Flight> flightList = flightService.bookFlights(searchParameters, passengersAndFlightDetails);

        Reservation reservation = Reservation.builder()
                .member(member)
                .payment(payment)
                .passengers(passengerList)
                .flights(flightList)
                .hasExtraLuggage(passengersAndFlightDetails.isHasExtraLuggage())
                .date(new Date())
                .build();

        reservationRepository.save(reservation);

        return "home";
    }

    @Override
    public HashMap<String, String> extractSearchParams(String s) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        HashMap<String, String> hashMap = objectMapper.readValue(s, new TypeReference<HashMap<String, String>>() {});

        return hashMap;
    }




}
