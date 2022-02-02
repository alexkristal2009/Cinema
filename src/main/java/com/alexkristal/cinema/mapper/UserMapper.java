package com.alexkristal.cinema.mapper;

import com.alexkristal.cinema.dto.UserDto;
import com.alexkristal.cinema.model.User;
import lombok.Builder;
import org.springframework.stereotype.Component;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Component
public class UserMapper {

    public UserDto mapUserToUserDto(User user) {
        return UserDto.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .login(user.getLogin())
                .build();
    }

}
