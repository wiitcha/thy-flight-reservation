package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.dto.ReservationDTO;
import com.iau.flight_management.model.entity.Flight;
import com.iau.flight_management.model.entity.Passenger;
import com.iau.flight_management.model.entity.Reservation;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ReservationMapper {

    private final FlightMapper flightMapper;
    private final PassengerMapper passengerMapper;

    private final PaymentMapper paymentMapper;
    private final MemberMapper memberMapper;

    public ReservationDTO toDto(Reservation reservation) {

        ReservationDTO reservationDTO = new ReservationDTO(reservation.getId(), reservation.getReservationCode(),
                reservation.getReservationType(), reservation.getDate());

        List<FlightDTO> flightDTOS = new ArrayList<>();
        for (Flight flight: reservation.getFlights()) {
            flightDTOS.add(flightMapper.toDto(flight));
        }
        reservationDTO.setFlights(flightDTOS);

        List<PassengerDTO> passengerDTOS = new ArrayList<>();
        for (Passenger passenger: reservation.getPassengers()) {
            passengerDTOS.add(passengerMapper.toDto(passenger));
        }
        reservationDTO.setPassengers(passengerDTOS);

        reservationDTO.setPayment(paymentMapper.toDto(reservation.getPayment()));
        reservationDTO.setMember(memberMapper.toDto(reservation.getMember()));

        return reservationDTO;
    }
}
