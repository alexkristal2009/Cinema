package com.alexkristal.cinema.mapper;

import com.alexkristal.cinema.dto.UserInfoForAdminDto;
import com.alexkristal.cinema.model.User;
import lombok.Builder;
import org.springframework.stereotype.Component;

@Builder
@Component
public class UserInfoForAdminMapper {

    public UserInfoForAdminDto mapUserToUserInfoForAdminDto(User user){
        return UserInfoForAdminDto.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .birthday(user.getBirthday())
                .phoneNumber(user.getPhoneNumber())
                .sex(user.getSex())
                .login(user.getLogin())
                .email(user.getEmail())
                .status(user.getStatus())
                .usersRoles(user.getUsersRoles())
                .usersTickets(user.getUsersTickets())
                .build();
    }
}
