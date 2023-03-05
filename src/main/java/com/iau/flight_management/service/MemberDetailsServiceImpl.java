package com.iau.flight_management.service;

import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service("memberDetailsService")
public class MemberDetailsServiceImpl implements MemberDetailsService{

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Member> memberOptional = memberRepository.findByEmail(email);

        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            member.setRoles(memberOptional.get().getRoles());
            return member;
        }
        throw new UsernameNotFoundException("User whose email is '" + email + "' not found.");
    }
}
