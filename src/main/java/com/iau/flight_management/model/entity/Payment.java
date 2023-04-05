package com.iau.flight_management.model.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "total")
    private BigDecimal total;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservations")
    private Card card;

    @OneToOne(mappedBy = "payment", fetch = FetchType.LAZY)
    private Reservation reservation;
}
