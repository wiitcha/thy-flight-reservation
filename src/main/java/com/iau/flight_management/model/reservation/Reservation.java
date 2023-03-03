package com.iau.flight_management.model.reservation;

import javax.persistence.*;

import com.iau.flight_management.model.user.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "status")
    private String status;

    @Column(name = "date")
    private Date date;

    @Column(name = "isFlexible")
    private boolean isFlexible;

    @Column(name = "hasExtraLuggage")
    private boolean hasExtraLuggage;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "reservation")
    private List<Flight> flights;

    @OneToOne
    @JoinColumn(name = "id")
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservations")
    public Member member;
}
