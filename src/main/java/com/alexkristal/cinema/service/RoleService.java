package com.alexkristal.cinema.service;

import com.alexkristal.cinema.dto.RoleDto;
import com.alexkristal.cinema.mapper.RoleMapper;
import com.alexkristal.cinema.model.Role;
import com.alexkristal.cinema.model.enums.Roles;
import com.alexkristal.cinema.repository.RolesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Service
public class RoleService {

    @Autowired
    private RolesRepository rolesRepository;

    @Autowired
    private RoleMapper roleMapper;

    public RoleDto getRoleByRoleName(Roles roleName){
        if (rolesRepository.existsRoleByRoleName(roleName)){
            return roleMapper.mapRoleToRoleDto(rolesRepository.getRoleByRoleName(roleName));
        }
        return null;
    }

    public List<RoleDto> getAllRoles(){
        List<Role> roleList = rolesRepository.findAll();
        List<RoleDto> roleDtoList = new ArrayList<>();

        if (!roleList.isEmpty()) {
            for (Role role : roleList) {
                roleDtoList.add(roleMapper.mapRoleToRoleDto(role));
            }
        } else {
            return null;
        }
        return roleDtoList;
    }

}
