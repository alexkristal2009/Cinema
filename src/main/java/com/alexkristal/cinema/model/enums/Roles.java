package com.alexkristal.cinema.model.enums;

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
