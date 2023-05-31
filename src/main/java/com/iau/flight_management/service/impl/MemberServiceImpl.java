package com.iau.flight_management.service.impl;

import com.iau.flight_management.mapper.MemberMapper;
import com.iau.flight_management.model.dto.MemberDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.MemberRepository;
import com.iau.flight_management.security.config.JwtService;
import com.iau.flight_management.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final JwtService jwtService;
    private final MemberMapper memberMapper;
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

    @Override
    public Optional<Member> extractUser(HttpServletRequest request) {
        String token = request.getSession().getAttribute("Authorization").toString();
        String email = jwtService.extractUsername(token);

        return memberRepository.findByEmail(email);
    }

    @Override
    public MemberDTO convertMemberToDto(Member member) {
        return memberMapper.toDto(member);
    }

    @Override
    public void saveMember(Member member) {
        memberRepository.save(member);
    }
}
