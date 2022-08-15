package com.project.drivers.enumeration;

public enum Status {
    DRIVER_UP("DRIVER_UP"),
    DRIVER_DOWN("DRIVER_DOWN");

    private final String status;

    Status(String status) {
        this.status = status;
    }

    public String getStatus() {
        return this.status;
    }
}
