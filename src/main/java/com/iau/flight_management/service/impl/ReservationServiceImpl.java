package com.iau.flight_management.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.mapper.ReservationMapper;
import com.iau.flight_management.model.FlightReservationModel;
import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.dto.ReservationDTO;
import com.iau.flight_management.model.entity.*;
import com.iau.flight_management.repository.ReservationRepository;
import com.iau.flight_management.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final PaymentService paymentService;
    private final PassengerService passengerService;
    private final FlightService flightService;
    private final ReservationMapper reservationMapper;

    @Override
    public String bookReservation(Member member, FlightReservationModel flightReservationModel) throws JsonProcessingException {
        List<FlightDTO> flightDTOS = flightReservationModel.getFlights();
        List<PassengerDTO> passengerDTOS = flightReservationModel.getPassengers();
        Payment payment = paymentService.makePayment(flightDTOS, flightReservationModel.getCardId(), passengerDTOS);

        if (payment != null) {
            List<Passenger> passengerList = passengerService.savePassengers(passengerDTOS);
            List<Flight> flightList = flightService.bookFlights(flightDTOS);

            Reservation reservation = Reservation.builder()
                    .member(member)
                    .payment(payment)
                    .passengers(passengerList)
                    .flights(flightList)
                    .reservationType(flightReservationModel.getFlightType())
                    .hasExtraLuggage(flightReservationModel.isHasExtraLuggage())
                    .reservationCode(generateReservationCode())
                    .date(formatDateOfToday())
                    .build();

            reservationRepository.save(reservation);

            return "redirect:/reservations?success";
        }
        return "redirect:/home?error";
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

    @Override
    public List<Reservation> findAllReservationsOfMember(Member member) {
        return reservationRepository.findAllByMemberIs(member);
    }

    @Override
    public String formatDateOfToday() {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy", Locale.ENGLISH);
        return formatter.format(date);
    }

    @Override
    public List<ReservationDTO> convertReservationsToDto(List<Reservation> reservations) {
        List<ReservationDTO> reservationDTOS = new ArrayList<>();

        for (Reservation reservation: reservations) {
            reservationDTOS.add(reservationMapper.toDto(reservation));
        }
        return reservationDTOS;
    }

    @Override
    public String cancelReservation(String referenceCode, Member member) {
        if (reservationRepository.existsByReservationCodeAndMemberIs(referenceCode, member)) {
            reservationRepository.deleteReservationByReservationCode(referenceCode);
            return "redirect:/reservations?delete";
        } else {
            return "reservations?error";
        }
    }
}
