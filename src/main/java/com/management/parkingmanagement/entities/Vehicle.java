package com.management.parkingmanagement.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "Vehicle")
@Table(name = "Vehicle")
@AllArgsConstructor
public class Vehicle implements Serializable {

    @Id
    @Column
    private String numberPlate;

    @Column
    private String model;

/*    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "email")
    private Client client;*/

}
