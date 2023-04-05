package com.iau.flight_management.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
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

    @Column(name = "card_holder")
    private String cardHolder;

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
