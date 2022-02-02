package com.alexkristal.cinema.dto;

import com.alexkristal.cinema.model.Role;
import com.alexkristal.cinema.model.Ticket;
import com.alexkristal.cinema.model.enums.Sex;
import com.alexkristal.cinema.model.enums.Status;
import lombok.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Builder
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoForAdminDto {

    private Long id;
    private String firstName;
    private String lastName;
//    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthday;
    private String phoneNumber;
    private Sex sex;
    private String login;
    private String email;
    private Status status;
    private List<Role> usersRoles;
    private List<Ticket> usersTickets;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInfoForAdminDto that = (UserInfoForAdminDto) o;
        return Objects.equals(id, that.id) && Objects.equals(firstName, that.firstName) && Objects.equals(lastName, that.lastName) && Objects.equals(birthday, that.birthday) && Objects.equals(phoneNumber, that.phoneNumber) && sex == that.sex && Objects.equals(login, that.login) && Objects.equals(email, that.email) && status == that.status && Objects.equals(usersRoles, that.usersRoles) && Objects.equals(usersTickets, that.usersTickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, phoneNumber, sex, login, email, status, usersRoles, usersTickets);
    }
}
