package com.alexkristal.cinema.controller;

import com.alexkristal.cinema.dto.TicketDto;
import com.alexkristal.cinema.dto.UserDto;
import com.alexkristal.cinema.dto.UserInfoForAdminDto;
import com.alexkristal.cinema.model.User;
import com.alexkristal.cinema.model.enums.Roles;
import com.alexkristal.cinema.model.enums.Sex;
import com.alexkristal.cinema.model.enums.Status;
import com.alexkristal.cinema.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public UserDto registrationUser(@RequestBody User user) {
        return userService.registrationUser(user);
    }

    @PostMapping("/users/admin")
    public UserDto registrationAdmin(@RequestBody User user) {
        return userService.registrationAdmin(user);
    }

    @PostMapping("/users/{login}")
    public UserDto addVipRoleToUser(@PathVariable String login) {
        return userService.addVipRoleToUser(login);
    }

    @PostMapping("/users/tickets/{userLogin}/{ticketId}")
    public List<TicketDto> addTicketToUserByTicketId(@PathVariable String userLogin, @PathVariable Long ticketId) {
        return userService.addTicketToUserByTicketId(userLogin, ticketId);
    }

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping("/getUserById/{id}")
//    public UserDto getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }

    @GetMapping("/users/{login}")
    public UserDto getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @DeleteMapping("/users")
    public String deleteAllUsers() {
        return userService.deleteAllUsers();
    }

//    @PostMapping("/deleteUserById/{id}")
//    public String deleteUserById(@PathVariable Long id) {
//        return userService.deleteUserById(id);
//    }

    @DeleteMapping("/users/{login}")
    public String deleteUserByLogin(@PathVariable String login) {
        return userService.deleteUserByLogin(login);
    }

    @PutMapping("/users/{login}")
    public UserDto updateUserByLogin(@PathVariable String login, @RequestBody User user) {
        return userService.updateUserByLogin(login, user);
    }

    // -------------------- Only for ADMIN ----------------------------

    @GetMapping("/users/admin/{login}")
    public UserInfoForAdminDto getAllUserInfo(@PathVariable String login) {
        return userService.getAllUserInfo(login);
    }

    @GetMapping("/users/admin/{roleName}")
    public List<UserInfoForAdminDto> getAllUserByRoleName(@PathVariable Roles roleName) {
        return userService.getAllUserByRoleName(roleName);
    }

    @GetMapping("/users/admin/{statusName}")
    public List<UserInfoForAdminDto> getAllUserByStatus(@PathVariable Status statusName) {
        return userService.getAllUserByStatus(statusName);
    }

    @GetMapping("/users/admin/{sexName}")
    public List<UserInfoForAdminDto> getAllUserBySex(@PathVariable Sex sexName) {
        return userService.getAllUserBySex(sexName);
    }

    @GetMapping("/users/admin/older/{years}")
    public List<UserInfoForAdminDto> getAllUsersOlderThan(@PathVariable int years) {
        return userService.getAllUsersOlderThan(years);
    }

    @GetMapping("/users/admin/under/{years}")
    public List<UserInfoForAdminDto> getAllUsersUnderThan(@PathVariable int years) {
        return userService.getAllUsersUnderThan(years);
    }

    //--------------------------------------------------------------------

    // -------------------- просто для теста ----------------------------

    @GetMapping("/users/{id}")
    public User getUserByIdTest(@PathVariable Long id) {
        return userService.getUserByIdTest(id);
    }

    //--------------------------------------------------------------------

}
