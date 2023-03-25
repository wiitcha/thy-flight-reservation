package com.iau.flight_management.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Member;

import java.util.HashMap;

public interface ReservationService {

    String bookReservation(Member member, String searchParams, PassengerDTO passengersAndFlightDetails) throws JsonProcessingException;

    HashMap<String, String> extractSearchParams(String s) throws JsonProcessingException;

    String generateReservationCode();


}
