package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.CardDTO;
import com.iau.flight_management.model.dto.MemberDTO;
import com.iau.flight_management.model.dto.RoleDTO;
import com.iau.flight_management.model.entity.Card;
import com.iau.flight_management.model.entity.Member;
import com.iau.flight_management.model.entity.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberMapper {

    private final RoleMapper roleMapper;
    private final CardMapper cardMapper;

    public MemberDTO toDto(Member member) {
        List<RoleDTO> roleDTOList = new ArrayList<>();
        List<CardDTO> cardDTOList = new ArrayList<>();

        for (Role role : member.getRoles()) {
            roleDTOList.add(roleMapper.toDto(role));
        }

        for (Card card : member.getCards()) {
            cardDTOList.add(cardMapper.toDto(card));
        }

        return MemberDTO.builder()
                .id(member.getId())
                    .name(member.getName())
                        .surname(member.getSurname())
                            .address(member.getAddress())
                                .country(member.getCountry())
                                    .email(member.getEmail())
                                        .academicTitle(member.getAcademicTitle())
                                            .cards(cardDTOList)
                                                .city(member.getCity())
                                                    .membershipNumber(member.getMembershipNumber())
                                                        .totalMiles(member.getTotalMiles())
                                                            .password(member.getPassword())
                                                                .phoneNumber(member.getPhoneNumber())
                                                                    .roles(roleDTOList).build();
    }
}
