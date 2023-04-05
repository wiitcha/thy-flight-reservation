package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.*;
import com.iau.flight_management.model.entity.*;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_NULL, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS
)
public interface EntityMapper {
    EntityMapper INSTANCE = Mappers.getMapper(EntityMapper.class);
    CardDTO map (Card value);
    Card map (CardDTO value);
    FlightDTO map (Flight value);
    Flight map (FlightDTO value);
    MemberDTO map (Member value);
    Member map (MemberDTO value);
    PassengerDTO map (Passenger value);
    Passenger map (PassengerDTO value);
    PaymentDTO map (Payment value);
    Payment map (PaymentDTO value);
    ReservationDTO map (Reservation value);
    Reservation map (ReservationDTO value);
    RoleDTO map (Role value);
    Role map (RoleDTO value);
}
