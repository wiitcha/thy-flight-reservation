package com.iau.flight_management.service;

import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;
    @Override
    public boolean existsByEmail(String email) {
        return memberRepository.existsByEmail(email);
    }
    @Override
    public Optional<Member> findByMemberId(Long id) {
        return memberRepository.findById(id);
    }
    @Override
    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }


}
