package com.iau.flight_management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.model.FlightReservationModel;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.model.entity.Passenger;
import com.iau.flight_management.model.entity.Reservation;

import java.util.HashMap;
import java.util.List;

public interface ReservationService {

    String bookReservation(Member member, FlightReservationModel flightReservationModel) throws JsonProcessingException;

    String generateReservationCode();

    List<Reservation> findAllReservationsOfMember(Member member);

    String formatDateOfToday();


}
