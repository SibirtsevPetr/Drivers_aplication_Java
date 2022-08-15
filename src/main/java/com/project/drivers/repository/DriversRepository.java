package com.project.drivers.repository;

import com.project.drivers.main.driver;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DriversRepository extends JpaRepository<driver, Long> {
    driver findByName(String name); // Name can't be the same
    //Drivers findByWorkingHours(String workingHours);
    //Drivers findByCurrentLocation(String currentLocation);

}
