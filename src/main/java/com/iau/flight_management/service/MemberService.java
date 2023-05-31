package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.MemberDTO;
import com.iau.flight_management.model.entity.Member;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

public interface MemberService {

    boolean existsByEmail(String email);
    Optional<Member> findByMemberId(Long id);
    Optional<Member> findByEmail(String email);
    Optional<Member> extractUser(HttpServletRequest request);
    MemberDTO convertMemberToDto(Member member);
    void saveMember(Member member);
}
