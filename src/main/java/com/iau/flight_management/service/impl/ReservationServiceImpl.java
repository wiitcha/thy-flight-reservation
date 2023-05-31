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
    private final MemberService memberService;
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
            addMiles(member, flightDTOS);

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

    @Override
    public Integer calculateMiles(int duration) {
        return duration * 12; // 12 km/min is speed of the plane in minutes
    }

    @Override
    public int convertTimeToMinutes(String time) {
        String[] parts = time.split("[hm\\s]+");
        int hours = Integer.parseInt(parts[0]);
        int minutes = 0;

        if (parts.length > 1) {
            hours = Integer.parseInt(parts[0]);
            minutes = Integer.parseInt(parts[1]);
        } else {
            minutes = Integer.parseInt(parts[0]);
        }

        return hours * 60 + minutes;
    }

    @Override
    public void addMiles(Member member, List<FlightDTO> flights) {
        Integer miles = 0;

        for (FlightDTO flight: flights) {
            int durationInMinutes = convertTimeToMinutes(flight.getDuration());
            miles += calculateMiles(durationInMinutes);
        }
        member.setTotalMiles(member.getTotalMiles() + miles);
        memberService.saveMember(member);
    }
}
