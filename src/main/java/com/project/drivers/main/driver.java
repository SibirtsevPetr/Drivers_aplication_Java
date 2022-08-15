package com.project.drivers.main;

import com.project.drivers.enumeration.Location;
import com.project.drivers.enumeration.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class driver {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotEmpty(message = "Driver must have a name!")
    private String name;
    private String personalData;
    private String workingHours;
    private String currentLocation;
    private String imageUrl;
    private Status status;
    private Location statusLocation;
}
