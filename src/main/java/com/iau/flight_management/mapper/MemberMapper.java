package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.MemberDTO;
import com.iau.flight_management.model.dto.RoleDTO;
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

    public MemberDTO toDto(Member member) {
        List<RoleDTO> roleDTOList = new ArrayList<>();

        for (Role role : member.getRoles()) {
            roleDTOList.add(roleMapper.toDto(role));
        }
        return new MemberDTO(member.getId(), member.getName(), member.getSurname(), roleDTOList);
    }
}
