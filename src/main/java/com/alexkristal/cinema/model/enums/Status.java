package com.alexkristal.cinema.model.enums;

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
