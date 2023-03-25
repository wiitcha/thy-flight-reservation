package com.iau.flight_management.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.*;
import com.iau.flight_management.repository.ReservationRepository;
import com.iau.flight_management.service.FlightService;
import com.iau.flight_management.service.PassengerService;
import com.iau.flight_management.service.PaymentService;
import com.iau.flight_management.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PaymentService paymentService;
    private final PassengerService passengerService;
    private final FlightService flightService;

    @Override
    public String bookReservation(Member member, String searchParams, PassengerDTO passengersAndFlightDetails) throws JsonProcessingException {
        HashMap<String, String> searchParameters = extractSearchParams(searchParams);
        String[] flights = passengersAndFlightDetails.getFlightDetails().split("/");

        Payment payment = paymentService.makePayment(flights, passengersAndFlightDetails, searchParameters);

        if (payment != null) {
            List<Passenger> passengerList = passengerService.savePassengers(passengersAndFlightDetails);
            List<Flight> flightList = flightService.bookFlights(searchParameters, passengersAndFlightDetails);

            Reservation reservation = Reservation.builder()
                    .member(member)
                    .payment(payment)
                    .passengers(passengerList)
                    .flights(flightList)
                    .hasExtraLuggage(passengersAndFlightDetails.isHasExtraLuggage())
                    .reservationCode(generateReservationCode())
                    .date(new Date())
                    .build();

            reservationRepository.save(reservation);

            return "redirect:/reservations?success";
        }
       return "redirect:/home?error";
    }

    @Override
    public HashMap<String, String> extractSearchParams(String s) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(s, new TypeReference<HashMap<String, String>>() {});
    }

    @Override
    public String generateReservationCode() {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        int length = 6;
        String reservationCode;

        do {
            sb.setLength(0);

            for (int i = 0; i < length; i++) {
                int index = random.nextInt(alphabet.length());
                char randomChar = alphabet.charAt(index);
                sb.append(randomChar);
            }
            reservationCode = sb.toString();
        } while (reservationRepository.existsByReservationCode(reservationCode));

        return reservationCode;
    }
}
