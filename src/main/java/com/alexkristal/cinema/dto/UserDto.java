package com.alexkristal.cinema.dto;

import lombok.*;

import java.util.Objects;

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private String firstName;
    private String lastName;
    private String login; // это уникальное поле чтобы опознать юзера

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserDto userDto = (UserDto) o;
        return Objects.equals(firstName, userDto.firstName) && Objects.equals(lastName, userDto.lastName) && Objects.equals(login, userDto.login);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, login);
    }
}
