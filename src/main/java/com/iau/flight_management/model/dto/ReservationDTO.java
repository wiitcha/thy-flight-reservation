package com.iau.flight_management.model.dto;

import com.iau.flight_management.model.entity.Flight;
import com.iau.flight_management.model.entity.Payment;
import com.iau.flight_management.model.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDTO {

    private Long id;
    private String status;
    private Date date;
    private boolean isFlexible;
    private boolean hasExtraLuggage;
    private Payment payment;
    public Member member;
    public List<Flight> flights;
}
