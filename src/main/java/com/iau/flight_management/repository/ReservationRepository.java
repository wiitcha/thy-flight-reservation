package com.iau.flight_management.repository;

import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByReservationCode(String reservationCode);

    List<Reservation> findAllByMemberIs(Member member);
}
