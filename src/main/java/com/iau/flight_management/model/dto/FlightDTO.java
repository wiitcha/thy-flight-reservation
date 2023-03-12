package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FlightDTO {

    private Long id;
    private String departureAirport;
    private String departureCity;
    private String destinationAirport;
    private String destinationCity;
    private Date date;
    private List<Reservation> reservations;
    private String arr_iata;
    private String dep_iata;
    private String arr_time;
    private String dep_time;
    private String duration;
    private String flight_iata;
    private String status;
    private int price;
}
