package com.alexkristal.cinema.mapper;

import com.alexkristal.cinema.dto.RoleDto;
import com.alexkristal.cinema.model.Role;
import lombok.Builder;
import org.springframework.stereotype.Component;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Component
public class RoleMapper {

    public RoleDto mapRoleToRoleDto(Role role) {
        return RoleDto.builder()
                .roleName(role.getRoleName())
                .build();
    }

}
