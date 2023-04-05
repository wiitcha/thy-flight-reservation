package com.iau.flight_management.repository;

import com.iau.flight_management.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    public Optional<Member> findByEmail(String email);
    public boolean existsByEmail(String email);
}
