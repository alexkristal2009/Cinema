package com.alexkristal.cinema.dto;

import com.alexkristal.cinema.model.enums.Roles;
import lombok.*;

import java.util.Objects;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class RoleDto {

    private Roles roleName;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleDto roleDto = (RoleDto) o;
        return roleName == roleDto.roleName;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roleName);
    }
}

