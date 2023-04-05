package com.iau.flight_management.model;

import com.iau.flight_management.model.dto.FlightDTO;
import com.iau.flight_management.model.dto.PassengerDTO;
import lombok.*;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlightReservationModel {
    private Long cardId;
    private List<PassengerDTO> passengers;
    private List<FlightDTO> flights;
    private boolean hasExtraLuggage;
    private String flightType;
}
