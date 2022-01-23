package com.alexkristal.cinema.repository;

import com.alexkristal.cinema.model.Role;
import com.alexkristal.cinema.model.enums.Roles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RolesRepository extends JpaRepository<Role, Long> {

    boolean existsRoleByRoleName(Roles rolesName);

    Role getRoleByRoleName(Roles rolesName);

}
