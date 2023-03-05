package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Reservation;
import com.iau.flight_management.model.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String country;
    private String city;
    private String address;
    private List<Role> roles;
    private List<Card> cards;
    public List<Reservation> reservations;
}
