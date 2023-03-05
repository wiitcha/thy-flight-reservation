package com.iau.flight_management.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "type")
    private String type;

    @Column(name = "number")
    private String number;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "exp_date")
    private String expDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Payment> payments;


}
