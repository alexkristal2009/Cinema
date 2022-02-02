package com.alexkristal.cinema.model.enums;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

public enum Roles {

    ADMIN("ADMIN"),
    VIP("VIP"),
    USER("USER");

    private final String rolesName;

    Roles(String rolesName) {
        this.rolesName = rolesName;
    }

    public String getRolesName() {
        return rolesName;
    }
}
