package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Reservation;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PassengerDTO {

    private Long id;
    private String[] name;
    private String[] surname;
    private String[] dateOfBirth;
    private String[] gender;
    private String[] phoneNumber;
    private String flightDetails;
    private String cardId;
    private boolean hasExtraLuggage;
    public List<Reservation> reservations;
}
