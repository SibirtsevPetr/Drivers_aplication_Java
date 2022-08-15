package com.project.drivers;

import com.project.drivers.enumeration.Location;
import com.project.drivers.enumeration.Status;
import com.project.drivers.main.driver;
import com.project.drivers.repository.DriversRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DriversApplication {

	public static void main(String[] args) {
		SpringApplication.run(DriversApplication.class, args);
	}


	@Bean
	CommandLineRunner run(DriversRepository driversRepository) {
		return args -> {
			driversRepository.save(new driver(null, "John Patison", "23, Hillel str. 29",
					"03-09", "6,11", "http://localhost:8080/server/image/p1.png", Status.DRIVER_UP, Location.DRIVER_INSIDE));
			driversRepository.save(new driver(null, "Jefri Bazzes", "23, Hillel str. 29",
					"18-19", "11,8", "http://localhost:8080/server/image/p1.png", Status.DRIVER_UP, Location.DRIVER_INSIDE));
			driversRepository.save(new driver(null, "Johi Micle", "23, Hillel str. 29",
					"18-07", "6,8", "http://localhost:8080/server/image/p1.png", Status.DRIVER_UP, Location.DRIVER_INSIDE));
		};
	}

}
