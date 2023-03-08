package com.iau.flight_management.service;

import com.iau.flight_management.model.entity.Member;

import java.util.Optional;

public interface MemberService {

    boolean existsByEmail(String email);
    Optional<Member> findByMemberId(Long id);
    Optional<Member> findByEmail(String email);
}
