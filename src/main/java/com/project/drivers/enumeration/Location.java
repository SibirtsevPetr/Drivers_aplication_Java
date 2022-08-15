package com.project.drivers.enumeration;

public enum Location {
    DRIVER_INSIDE("DRIVER_INSIDE"),
    DRIVER_OUTSIDE("DRIVER_OUTSIDE");

    private final String location;

    Location(String location) {
        this.location = location;
    }

    public String getLocation() {
        return this.location;
    }
}
