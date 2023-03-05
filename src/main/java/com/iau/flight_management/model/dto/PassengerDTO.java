package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {

    private Long id;
    private String name;
    private String surname;
    private Date dateOfBirth;
    private String gender;
    private String phoneNumber;
    public List<Reservation> reservations;
}
