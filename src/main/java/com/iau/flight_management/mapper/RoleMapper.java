package com.iau.flight_management.mapper;

import com.iau.flight_management.model.dto.RoleDTO;
import com.iau.flight_management.model.entity.Role;
import org.springframework.stereotype.Component;

@Component
public class RoleMapper {

    public RoleDTO toDto(Role role) {
        return new RoleDTO(role.getId(), role.getTitle());
    }
}
