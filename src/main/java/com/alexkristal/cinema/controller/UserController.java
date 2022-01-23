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

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/registrationUser")
    public UserDto registrationUser(@RequestBody User user) {
        return userService.registrationUser(user);
    }

    @PostMapping("/registrationAdmin")
    public UserDto registrationAdmin(@RequestBody User user) {
        return userService.registrationAdmin(user);
    }

    @PostMapping("/addVipRoleToUser/{login}")
    public UserDto addVipRoleToUser(@PathVariable String login) {
        return userService.addVipRoleToUser(login);
    }

    @PostMapping("/addTicketToUserBiTicketId/{userLogin}/{ticketId}")
    public List<TicketDto> addTicketToUserBiTicketId(@PathVariable String userLogin, @PathVariable Long ticketId) {
        return userService.addTicketToUserBiTicketId(userLogin, ticketId);
    }

    @GetMapping("/getAllUsers")
    public List<UserDto> getAllUsers() {
        return userService.getAllUsers();
    }

//    @GetMapping("/getUserById/{id}")
//    public UserDto getUserById(@PathVariable Long id) {
//        return userService.getUserById(id);
//    }

    @GetMapping("/getUserByLogin/{login}")
    public UserDto getUserByLogin(@PathVariable String login) {
        return userService.getUserByLogin(login);
    }

    @PostMapping("/deleteAllUsers")
    public String deleteAllUsers() {
        return userService.deleteAllUsers();
    }

//    @PostMapping("/deleteUserById/{id}")
//    public String deleteUserById(@PathVariable Long id) {
//        return userService.deleteUserById(id);
//    }

    @PostMapping("/deleteUserByLogin/{login}")
    public String deleteUserByLogin(@PathVariable String login) {
        return userService.deleteUserByLogin(login);
    }

    @PostMapping("/updateUserByLogin/{login}")
    public UserDto updateUserByLogin(@PathVariable String login, @RequestBody User user) {
        return userService.updateUserByLogin(login, user);
    }

    // -------------------- Only for ADMIN ----------------------------

    @GetMapping("/getAllUserInfo/{login}")
    public UserInfoForAdminDto getAllUserInfo(@PathVariable String login) {
        return userService.getAllUserInfo(login);
    }

    @GetMapping("/getAllUserByRoleName/{roleName}")
    public List<UserInfoForAdminDto> getAllUserByRoleName(@PathVariable Roles roleName) {
        return userService.getAllUserByRoleName(roleName);
    }

    @GetMapping("/getAllUserByStatus/{statusName}")
    public List<UserInfoForAdminDto> getAllUserByStatus(@PathVariable Status statusName) {
        return userService.getAllUserByStatus(statusName);
    }

    @GetMapping("/getAllUserBySex/{sexName}")
    public List<UserInfoForAdminDto> getAllUserBySex(@PathVariable Sex sexName) {
        return userService.getAllUserBySex(sexName);
    }

    @GetMapping("/getAllUsersOlderThan/{years}")
    public List<UserInfoForAdminDto> getAllUsersOlderThan(@PathVariable int years) {
        return userService.getAllUsersOlderThan(years);
    }

    @GetMapping("/getAllUsersUnderThan/{years}")
    public List<UserInfoForAdminDto> getAllUsersUnderThan(@PathVariable int years) {
        return userService.getAllUsersUnderThan(years);
    }

    //--------------------------------------------------------------------

    // -------------------- просто для теста ----------------------------

    @GetMapping("/getUserByIdTest/{id}")
    public User getUserByIdTest(@PathVariable Long id) {
        return userService.getUserByIdTest(id);
    }

    //--------------------------------------------------------------------

}
