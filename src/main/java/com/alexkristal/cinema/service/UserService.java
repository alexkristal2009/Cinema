package com.alexkristal.cinema.service;


import com.alexkristal.cinema.dto.TicketDto;
import com.alexkristal.cinema.dto.UserDto;
import com.alexkristal.cinema.dto.UserInfoForAdminDto;
import com.alexkristal.cinema.mapper.TicketMapper;
import com.alexkristal.cinema.mapper.UserInfoForAdminMapper;
import com.alexkristal.cinema.mapper.UserMapper;
import com.alexkristal.cinema.model.Role;
import com.alexkristal.cinema.model.Ticket;
import com.alexkristal.cinema.model.User;
import com.alexkristal.cinema.model.enums.Roles;
import com.alexkristal.cinema.model.enums.Sex;
import com.alexkristal.cinema.model.enums.Status;
import com.alexkristal.cinema.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Service
@Transactional
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private TicketMapper ticketMapper;

    @Autowired
    private UserInfoForAdminMapper userInfoForAdminMapper;

    /**
     * method register
     *
     * @param user that is <strong>user</strong> entity
     * @return userdto
     */

    public UserDto registrationUser(User user) {

        user.setStatus(Status.ACTIVE); // добавляем дефолтный статус ACTIVE
        user.setUsersRoles(List.of(rolesRepository.getRoleByRoleName(Roles.USER))); // добавляем дефолтную роль USER

        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    public UserDto registrationAdmin(User user){

        user.setStatus(Status.ACTIVE); // добавляем дефолтный статус ACTIVE
        user.setUsersRoles(List.of(rolesRepository.getRoleByRoleName(Roles.ADMIN))); // добавляем дефолтную роль ADMIN

        return userMapper.mapUserToUserDto(userRepository.save(user));
    }

    public UserDto addVipRoleToUser(String login){

        User user = userRepository.getUserByLogin(login);
        if (user != null) {
            user.getUsersRoles().add(rolesRepository.getRoleByRoleName(Roles.VIP)); // добавляем роль VIP
            return userMapper.mapUserToUserDto(userRepository.save(user));
        }
        return null;

    }

    public List<TicketDto> addTicketToUserByTicketId(String userLogin, Long ticketId){

        if (!userRepository.existsByLogin(userLogin)) {
            return null;
        }

        if (!ticketRepository.existsTicketById(ticketId)) {
            return null;
        }

        User user = userRepository.getUserByLogin(userLogin);
        user.setUsersTickets(List.of(ticketRepository.getById(ticketId)));

        List<Ticket> ticketList = user.getUsersTickets();
        List<TicketDto> ticketDtoList = new ArrayList<>();

        for (Ticket ticket : ticketList) {
            ticketDtoList.add(ticketMapper.mapTicketToTicketDto(ticket));
        }

        return ticketDtoList;
    }

    public List<UserDto> getAllUsers() {

        List<User> userList = userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        if (!userList.isEmpty()) {
            for (User user : userList) {
                userDtoList.add(userMapper.mapUserToUserDto(user));
            }
        } else {
            return null;
        }

        return userDtoList;
    }

//    public UserDto getUserById (Long id) {
//        if (userRepository.existsById(id)){
//            return userMapper.mapUserToUserDto(userRepository.getUserById(id));
//        }
//        return null;
//    }

    public UserDto getUserByLogin(String login) {
        User user = userRepository.getUserByLogin(login);
        if (user != null){
            return userMapper.mapUserToUserDto(user);
        }
        return null;
    }

    public String deleteAllUsers() {
        userRepository.deleteAll();
        return "All users deleted!";
    }

//    public String deleteUserById (Long id) {
//        if (userRepository.existsById(id)) {
//            userRepository.deleteById(id);
//            return String.format("User with ID - %d deleted successfully!", id);
//        } else {
//            return String.format("There is no user with ID - %d.", id);
//        }
//    }

    public String deleteUserByLogin (String login) {
        if (userRepository.existsByLogin(login)) {
            userRepository.deleteByLogin(login);
            return String.format("User login - %s deleted successfully!", login);
        } else {
            return String.format("There is no user login - %s!", login);
        }
    }

    public UserDto updateUserByLogin(String login, User user) {

        if (userRepository.existsByLogin(login)) {

            User tempUser = userRepository.getUserByLogin(login);

            user.setId(tempUser.getId());
            user.setStatus(Status.ACTIVE); // добавляем дефолтный статус ACTIVE
            user.setUsersRoles(List.of(rolesRepository.getRoleByRoleName(Roles.USER))); // добавляем дефолтную роль USER

            return userMapper.mapUserToUserDto(userRepository.save(user));
        }
        return null;
    }

    //------------------------ Only for ADMIN -------------------------------------

    public UserInfoForAdminDto getAllUserInfo(String login){
        if (userRepository.existsByLogin(login)) {
            User tempUser = userRepository.getUserByLogin(login);
            return userInfoForAdminMapper.mapUserToUserInfoForAdminDto(tempUser);
        }
        return null;
    }

    public List<UserInfoForAdminDto> getAllUserByRoleName(Roles roleName){

        if (!rolesRepository.existsRoleByRoleName(roleName)) {
            return null;
        }


        List<User> listUsers = userRepository.findAll();
        List<UserInfoForAdminDto> listUsersForAdminDto = new ArrayList<>();

        if (listUsers.isEmpty()) {
            return null;
        }

        for (User user : listUsers) {

            for (Role usersRole : user.getUsersRoles()) {

                if (usersRole.getRoleName().equals(roleName)) {
                    listUsersForAdminDto.add(userInfoForAdminMapper.mapUserToUserInfoForAdminDto(user));
                }

            }

        }

        return listUsersForAdminDto;
    }

    public List<UserInfoForAdminDto> getAllUserByStatus(Status statusName){

        List<User> listUsers = userRepository.findAll();
        List<UserInfoForAdminDto> listUsersForAdminDto = new ArrayList<>();

        if (listUsers.isEmpty()) {
            return null;
        }

        for (User user : listUsers) {

            if (user.getStatus().equals(statusName)) {
                listUsersForAdminDto.add(userInfoForAdminMapper.mapUserToUserInfoForAdminDto(user));
            }

        }

        return listUsersForAdminDto;
    }

    public List<UserInfoForAdminDto> getAllUserBySex(Sex sexName){

        List<User> listUsers = userRepository.findAll();
        List<UserInfoForAdminDto> listUsersForAdminDto = new ArrayList<>();

        if (listUsers.isEmpty()) {
            return null;
        }

        for (User user : listUsers) {

            if (user.getSex().equals(sexName)) {
                listUsersForAdminDto.add(userInfoForAdminMapper.mapUserToUserInfoForAdminDto(user));
            }

        }

        return listUsersForAdminDto;
    }

    public List<UserInfoForAdminDto> getAllUsersOlderThan(int years) {

        if (years < 1){
            return null;
        }

        List<User> listUsers = userRepository.findAll();
        List<UserInfoForAdminDto> listUsersForAdminDto = new ArrayList<>();

        if (listUsers.isEmpty()) {
            return null;
        }

        LocalDate dateOfBirthday;
        LocalDate nowDate = LocalDate.now();

        for (User user : listUsers) {

            dateOfBirthday = user.getBirthday();
            Period period = Period.between(dateOfBirthday, nowDate);
            int userYears = period.getYears();

            if (userYears >= years){
                listUsersForAdminDto.add(userInfoForAdminMapper.mapUserToUserInfoForAdminDto(user));
            }
        }

        return listUsersForAdminDto;

    }

    public List<UserInfoForAdminDto> getAllUsersUnderThan(int years) {

        if (years < 1){
            return null;
        }

        List<User> listUsers = userRepository.findAll();
        List<UserInfoForAdminDto> listUsersForAdminDto = new ArrayList<>();

        if (listUsers.isEmpty()) {
            return null;
        }

        LocalDate dateOfBirthday;
        LocalDate nowDate = LocalDate.now();

        for (User user : listUsers) {

            dateOfBirthday = user.getBirthday();
            Period period = Period.between(dateOfBirthday, nowDate);
            int userYears = period.getYears();

            if (userYears < years){
                listUsersForAdminDto.add(userInfoForAdminMapper.mapUserToUserInfoForAdminDto(user));
            }
        }

        return listUsersForAdminDto;

    }

    //-------------------------------------------------------------------------------

    // -------------------- просто для теста ----------------------------

    public User getUserByIdTest (Long id) {
        if (userRepository.existsById(id)){
            return userRepository.getUserById(id);
        }
        return null;
    }

    //--------------------------------------------------------------------

}
