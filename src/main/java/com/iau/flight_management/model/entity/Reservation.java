package com.iau.flight_management.model.entity;

import javax.persistence.*;

import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reservation_code")
    private String reservationCode;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;

    @Column(name = "hasExtraLuggage")
    private boolean hasExtraLuggage;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservations")
    public Member member;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "flight_reservation",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "flight_id"))
    public List<Flight> flights;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "reservation_passenger",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "passenger_id"))
    public List<Passenger> passengers;
}
