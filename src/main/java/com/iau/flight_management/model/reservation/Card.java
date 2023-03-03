package com.iau.flight_management.model.reservation;

import com.iau.flight_management.model.user.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "card")
public class Card {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "brand")
    private String brand;

    @Column(name = "number")
    private String number;

    @Column(name = "cvv")
    private String cvv;

    @Column(name = "exp_date")
    private Date expDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cards")
    private Member member;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "card")
    private List<Payment> payments;


}
