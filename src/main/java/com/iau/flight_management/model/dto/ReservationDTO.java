package com.iau.flight_management.model.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {
    private MemberDTO member;
    private Long id;
    private String reservationCode;
    private String reservationType;
    private String date;
    private boolean hasExtraLuggage;
    private PaymentDTO payment;
    private List<FlightDTO> flights;
    private List<PassengerDTO> passengers;

    public ReservationDTO(Long id, String reservationCode, String reservationType, String date) {
        this.id = id;
        this.reservationCode = reservationCode;
        this.reservationType = reservationType;
        this.date = date;
    }
}
