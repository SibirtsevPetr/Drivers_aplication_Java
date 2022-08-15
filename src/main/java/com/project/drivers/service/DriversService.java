package com.project.drivers.service;

import com.project.drivers.main.driver;

import java.io.IOException;
import java.util.Collection;

public interface DriversService {
    // create a driver
    driver create(driver driver);

    // reload status
    driver reload(String name) throws IOException;

    // limits for shown drivers
    Collection<driver> list(int limit);


    // get a driver
    driver get(Long id);

    // update driver information
    driver update(driver driver);

    // delete driver
    Boolean delete(Long id);
}
