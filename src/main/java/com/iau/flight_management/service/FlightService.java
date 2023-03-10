package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.Airport;

import java.io.IOException;
import java.util.List;

public interface FlightService {

    List<Airport> getAirports() throws IOException, InterruptedException;
}
