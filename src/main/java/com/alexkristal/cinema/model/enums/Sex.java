package com.alexkristal.cinema.model.enums;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

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
