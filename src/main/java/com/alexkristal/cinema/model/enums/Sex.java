package com.alexkristal.cinema.model.enums;

public enum Sex {

    MALE("MALE"),
    FEMALE("FEMALE");

    private final String sexName;

    Sex(String sexName) {
        this.sexName = sexName;
    }

    public String getSexName() {
        return sexName;
    }
}
