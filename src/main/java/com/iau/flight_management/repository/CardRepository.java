package com.iau.flight_management.repository;

import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardRepository extends JpaRepository<Card, Long> {


    List<Card> findAllByMemberIs(Member member);
    boolean existsCardByIdAndMemberIs(Long id, Member member);

}
