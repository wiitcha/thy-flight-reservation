package com.iau.flight_management.repository;

import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.model.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    boolean existsByReservationCode(String reservationCode);

    List<Reservation> findAllByMemberIs(Member member);
    boolean existsByReservationCodeAndMemberIs(String reservationCode, Member member);

    @Transactional
    void deleteReservationByReservationCode(String reservationCode);
}
