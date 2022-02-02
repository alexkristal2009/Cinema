package com.alexkristal.cinema.repository;

import com.alexkristal.cinema.model.Role;
import com.alexkristal.cinema.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

    boolean existsRoleByRoleName(Roles rolesName);

    Role getRoleByRoleName(Roles rolesName);

}
