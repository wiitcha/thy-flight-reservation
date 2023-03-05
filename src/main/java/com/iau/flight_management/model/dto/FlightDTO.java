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
    private String departure;
    private String destination;
    private Time departureTime;
    private Time arrivalTime;
    private Date date;
    private List<Reservation> reservations;
}
