package com.iau.flight_management.service;

import com.iau.flight_management.model.dto.MemberDTO;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.repository.MemberRepository;
import com.iau.flight_management.repository.RoleRepository;
import com.iau.flight_management.security.auth.AuthenticationRequest;
import com.iau.flight_management.security.config.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final MemberDetailsService memberDetailsService;
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final JwtService jwtService;

    public String register(MemberDTO request) {
        var user = Member.builder()
                .name(request.getName())
                .surname(request.getSurname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .country(request.getCountry())
                .city(request.getCity())
                .address(request.getAddress())
                .roles(new ArrayList<>(roleRepository.findAllByTitleIs("USER")))
                .build();

        memberRepository.save(user);
       return jwtService.generateToken(user);
    }

    public String authenticate(AuthenticationRequest request) {
        UserDetails user = null;
        if (StringUtils.hasLength(request.getEmail())
                && StringUtils.hasLength(request.getPassword())
        ) {
            user = this.memberDetailsService.loadUserByUsername(request.getEmail());
        }
        if (user == null || !passwordEncoder.matches(request.getPassword(),user.getPassword())) {
            return null;
        }

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                user,
                null,
                user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        return this.jwtService.generateToken(user);
    }
}