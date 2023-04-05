package com.iau.flight_management.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PassengerDTO {
    private Long id;
    private String name;
    private String surname;
    private String dateOfBirth;
    private String gender;
    private String phoneNumber;
    private List<ReservationDTO> reservations;

    public PassengerDTO(Long id, String name, String surname, String dateOfBirth, String gender, String phoneNumber) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }
}
