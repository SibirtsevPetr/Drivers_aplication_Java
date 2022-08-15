package com.project.drivers.service.implantation;

import com.project.drivers.enumeration.Location;
import com.project.drivers.main.driver;
import com.project.drivers.repository.DriversRepository;
import com.project.drivers.service.DriversService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;
import java.util.Random;
import org.json.simple.JSONObject;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.DateFormat;

import static com.project.drivers.enumeration.Location.DRIVER_INSIDE;
import static com.project.drivers.enumeration.Location.DRIVER_OUTSIDE;
import static com.project.drivers.enumeration.Status.DRIVER_DOWN;
import static com.project.drivers.enumeration.Status.DRIVER_UP;
import static java.lang.Boolean.TRUE;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class DriversServiceImpl implements DriversService {

    private final DriversRepository driversRepository;

    @Override
    public driver create(driver driver) {
        log.info("Saving new driver {}", driver.getName());
        driver.setImageUrl(setDriversImageUrl());
        return driversRepository.save(driver);
    }

    @Override
    public driver reload(String name) throws IOException {
        log.info("Reloading drivers status for {}", name);
        driver driver1 = driversRepository.findByName(name);
        Date date = new Date();
        DateFormat df = new SimpleDateFormat("HH");
        String hour = df.format(date);
        int localHour = Character.getNumericValue(hour.charAt(0)) * 10 + Character.getNumericValue(hour.charAt(1));
        String[] currentL = (driver1.getCurrentLocation()).split(",");
        int locationX = Integer.parseInt(currentL[0]);
        int locationY = Integer.parseInt(currentL[1]);
        if (locationX < 10 & locationY < 10) {
            driver1.setStatusLocation(DRIVER_INSIDE);
        } else {
            driver1.setStatusLocation(DRIVER_OUTSIDE);
        }
        String workingHours = driver1.getWorkingHours();
        int startTime = Character.getNumericValue(workingHours.charAt(0)) * 10 + Character.getNumericValue(workingHours.charAt(1));
        int endTime = Character.getNumericValue(workingHours.charAt(3)) * 10 + Character.getNumericValue(workingHours.charAt(4));
        if (startTime < endTime) {
            if (startTime <= localHour && endTime > localHour) {
                driver1.setStatus(DRIVER_UP);
            } else {
                driver1.setStatus(DRIVER_DOWN);
            }
        } else {
            if (endTime > localHour  || startTime <= localHour) {
                driver1.setStatus(DRIVER_UP);
            } else {
                driver1.setStatus(DRIVER_DOWN);
            }
            if (startTime == endTime) {
                driver1.setStatus(DRIVER_UP);
            }
        }

        driversRepository.save(driver1);
        return driver1;
    }

    @Override
    public Collection<driver> list(int limit) {
        log.info("Fetching all drivers");
        return driversRepository.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public driver get(Long id) {
        log.info("Fetching server by id: {}", id);
        return driversRepository.findById(id).get();
    }

    @Override
    public driver update(driver driver) {
        log.info("Updating driver {}", driver.getName());
        return driversRepository.save(driver);
    }

    @Override
    public Boolean delete(Long id) {
        log.info("Deleting driver {}", id);
        driversRepository.deleteById(id);
        return TRUE;
    }

    private String setDriversImageUrl() {
        String[]  imageNames = {"p1.png", "p2.png", "p3.png", "p4.png", "p5.png", "p6.png",
                "p7.png", "p8.png", "p9.png", "p10.png", "p11.png", "p12.png", "p13.png", "p14.png",
                "p15.png", "p16.png", "p17.png", "p18.png"};
        return ServletUriComponentsBuilder.fromCurrentContextPath().path("/drivers/image/" + imageNames[new Random().nextInt(19)]).toUriString();
    }
}
