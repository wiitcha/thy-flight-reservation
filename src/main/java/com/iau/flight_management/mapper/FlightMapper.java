package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.entity.Flight;
import org.springframework.stereotype.Component;

@Component
public class FlightMapper {
    public FlightDTO toDto(Flight flight) {
        return new FlightDTO(flight.getId(), flight.getDepartureAirport(),
                flight.getDepartureAirportIataCode(), flight.getDepartureCity(), flight.getArrivalAirport(),
                flight.getArrivalAirportIataCode(), flight.getArrivalCity(), flight.getDepartureTime(),
                flight.getArrivalTime(),flight.getDate(), flight.getFlightIataCode(), flight.getDuration(), flight.getPrice());
    }
}
