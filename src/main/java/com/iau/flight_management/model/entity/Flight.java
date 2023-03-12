package com.iau.flight_management.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "flight")
public class Flight {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "departure_airport")
    private String departureAirport;

    @Column(name = "departure_airport_iata")
    private String departureAirportIataCode;

    @Column(name = "departure_city")
    private String departureCity;

    @Column(name = "arrival_airport")
    private String arrivalAirport;

    @Column(name = "arrival_airport_iata")
    private String arrivalAirportIataCode;

    @Column(name = "arrival_city")
    private String arrivalCity;

    @Column(name = "departure_time")
    private Time departureTime;

    @Column(name = "arrival_time")
    private Time arrivalTime;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @ManyToMany(mappedBy = "flights", fetch = FetchType.LAZY)
    private List<Reservation> reservations;
}
