package com.iau.flight_management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.model.FlightReservationModel;
import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.dto.ReservationDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.model.entity.Reservation;

import java.util.List;

public interface ReservationService {

    String bookReservation(Member member, FlightReservationModel flightReservationModel) throws JsonProcessingException;

    String generateReservationCode();

    List<Reservation> findAllReservationsOfMember(Member member);

    String formatDateOfToday();

    List<ReservationDTO> convertReservationsToDto(List<Reservation> reservations);

    String cancelReservation(String referenceCode, Member member);
    Integer calculateMiles(int duration);
    int convertTimeToMinutes(String time);
    void addMiles(Member member, List<FlightDTO> flights);


}
