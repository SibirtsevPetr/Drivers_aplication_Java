package com.project.drivers.resource;


import com.project.drivers.enumeration.Status;
import com.project.drivers.main.driver;
import com.project.drivers.main.Response;
import com.project.drivers.service.DriversService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

import static java.time.LocalDateTime.now;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class Resource {
    private final DriversService driversService;

    @GetMapping("/list")
    public ResponseEntity<Response> getDrivers() {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("drivers", driversService.list(30)))
                        .message("Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping("/reload/{name}")
    public ResponseEntity<Response> reloadDrivers(@PathVariable("name") String name) throws IOException {
        driver driver1 = driversService.reload(name);
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("driver", driver1))
                        .message(driver1.getStatus() == Status.DRIVER_UP ? "Driver is working" : "Driver is not working")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @PostMapping ("/save")
    public ResponseEntity<Response> saveDrivers(@RequestBody @Valid driver driver){
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("drivers", driversService.create(driver)))
                        .message("Driver created")
                        .status(CREATED)
                        .statusCode(CREATED.value())
                        .build()
        );
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getDrivers(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("drivers", driversService.get(id)))
                        .message("Retrieved")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @DeleteMapping ("/delete/{id}")
    public ResponseEntity<Response> deleteDrivers(@PathVariable("id") Long id) {
        return ResponseEntity.ok(
                Response.builder()
                        .timeStamp(now())
                        .data(Map.of("deleted", driversService.delete(id)))
                        .message("Deleted")
                        .status(OK)
                        .statusCode(OK.value())
                        .build()
        );
    }

    @GetMapping (path = "/image/{fileName}", produces = IMAGE_PNG_VALUE)
    public byte[] deleteDrivers(@PathVariable("fileName") String fileName) throws IOException {
        return Files.readAllBytes(Paths.get("/Users/mac/Downloads/drivers/src/main/resources/DriverPhoto/" + fileName));
    }
}
