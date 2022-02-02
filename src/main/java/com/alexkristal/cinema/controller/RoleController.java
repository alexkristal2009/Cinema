package com.alexkristal.cinema.controller;

import com.alexkristal.cinema.dto.RoleDto;
import com.alexkristal.cinema.model.enums.Roles;
import com.alexkristal.cinema.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@RestController
@RequestMapping("/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping("/roles/{roleName}")
    public RoleDto getRoleByRoleName(@PathVariable Roles roleName) {
        return roleService.getRoleByRoleName(roleName);
    }

    @GetMapping("/roles")
    public List<RoleDto> getAllRoles() {
        return roleService.getAllRoles();
    }

}
