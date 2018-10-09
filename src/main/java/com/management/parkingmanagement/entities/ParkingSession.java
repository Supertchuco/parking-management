package com.management.parkingmanagement.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Entity(name = "ParkingSession")
@Table(name = "ParkingSession")
@NoArgsConstructor
public class ParkingSession implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private int sessionId;

    @Column
    @Temporal(TemporalType.TIME)
    private Date started;

    @Column
    @Temporal(TemporalType.TIME)
    private Date finished;

    @OneToOne
    @JoinColumn(name = "numberPlate")
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "parkId")
    private Park park;
}
