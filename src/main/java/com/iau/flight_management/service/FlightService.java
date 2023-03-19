package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.dto.PassengerDTO;
import com.iau.flight_management.model.entity.Flight;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface FlightService {

    //List<Airport> getAirports() throws IOException, InterruptedException;
    HashMap<String, String> extractSearchParameters(MultiValueMap<String, String> formData, Model model) throws IOException, InterruptedException;
    String generateFlightSearchAPIToken(HashMap<String, String> parameters);
    List<FlightDTO> getSearchedFlights(HashMap<String, String> parameters) throws IOException, InterruptedException;
    String convertDurationToHours(int duration);
    List<Flight> bookFlights(HashMap<String, String> searchParameters, PassengerDTO passengersAndFlightDetails);
}
