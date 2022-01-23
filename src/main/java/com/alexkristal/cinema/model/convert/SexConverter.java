package com.alexkristal.cinema.model.convert;

import com.alexkristal.cinema.model.enums.Sex;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class SexConverter implements AttributeConverter<Sex, String> {

    @Override
    public String convertToDatabaseColumn(Sex sex) {
        if (sex == null) {
            return null;
        }
        return sex.getSexName();
    }

    @Override
    public Sex convertToEntityAttribute(String sex) {
        if (sex == null) {
            return null;
        }

        return Stream.of(Sex.values())
                .filter(s -> s.getSexName().equals(sex))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
