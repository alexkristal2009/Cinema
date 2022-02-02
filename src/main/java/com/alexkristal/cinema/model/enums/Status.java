package com.alexkristal.cinema.model.enums;

/**
 * @author Alex Kristal
 * @created 02.02.2022
 * @email alexkristal2009@gmail.com
 */

public enum Status {

    ACTIVE("ACTIVE"),
    DELETED("DELETED");

    private final String statusName;

    Status(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }
}
