package com.alexkristal.cinema.model.convert;

import com.alexkristal.cinema.model.enums.Roles;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Converter(autoApply = true)
public class RolesConverter implements AttributeConverter<Roles, String> {

    @Override
    public String convertToDatabaseColumn(Roles roles) {
        if (roles == null) {
            return null;
        }
        return roles.getRolesName();
    }

    @Override
    public Roles convertToEntityAttribute(String rolesName) {
        if (rolesName == null) {
            return null;
        }

        return Stream.of(Roles.values())
                .filter(r -> r.getRolesName().equals(rolesName))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }
}
