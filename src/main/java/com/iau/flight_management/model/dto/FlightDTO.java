package com.iau.flight_management.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {
    private Long id;
    private String departureAirport;
    @JsonProperty("dep_iata")
    private String departureAirportIataCode;
    private String departureCity;
    private String arrivalAirport;
    @JsonProperty("arr_iata")
    private String arrivalAirportIataCode;
    private String arrivalCity;
    @JsonProperty("dep_time")
    private String departureTime;
    @JsonProperty("arr_time")
    private String arrivalTime;
    private String date;
    @JsonProperty("flight_iata")
    private String flightIataCode;
    private String duration;
    private BigDecimal price;
    private List<ReservationDTO> reservations;

    public FlightDTO(Long id, String departureAirport, String departureAirportIataCode, String departureCity, String arrivalAirport, String arrivalAirportIataCode, String arrivalCity, String departureTime, String arrivalTime, String date, String flightIataCode, String duration, BigDecimal price) {
        this.id = id;
        this.departureAirport = departureAirport;
        this.departureAirportIataCode = departureAirportIataCode;
        this.departureCity = departureCity;
        this.arrivalAirport = arrivalAirport;
        this.arrivalAirportIataCode = arrivalAirportIataCode;
        this.arrivalCity = arrivalCity;
        this.departureTime = departureTime;
        this.arrivalTime = arrivalTime;
        this.date = date;
        this.flightIataCode = flightIataCode;
        this.duration = duration;
        this.price = price;
    }
}
