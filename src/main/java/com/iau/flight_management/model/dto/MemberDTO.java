package com.iau.flight_management.model.dto;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO implements UserDetails {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private String country;
    private String city;
    private String address;
    private String phoneNumber;
    private String academicTitle;
    private Integer totalMiles;
    private Long membershipNumber;
    private List<RoleDTO> roles;
    private List<CardDTO> cards;
    private List<ReservationDTO> reservations;

    public MemberDTO(Long id, String name, String surname, List<RoleDTO> roles) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.roles = roles;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roles.toString()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
