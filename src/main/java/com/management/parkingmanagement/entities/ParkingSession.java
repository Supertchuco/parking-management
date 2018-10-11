package com.management.parkingmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "ParkingSession")
@Table(name = "ParkingSession")
@AllArgsConstructor
public class ParkingSession implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private int sessionId;

    @Column
    private Date started;

    @Column
    private Date finished;

    @Column
    private String plateNumber;

    @Column
    private int parkId;

    public ParkingSession(Date started, String plateNumber, int parkId) {
        this.started = started;
        this.plateNumber = plateNumber;
        this.parkId = parkId;
    }
}
