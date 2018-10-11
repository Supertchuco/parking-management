package com.management.parkingmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "Vehicle")
@Table(name = "Vehicle")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle implements Serializable {

    @Id
    @Column
    private String plateNumber;

    @Column
    private String model;

    @ManyToOne
    @JsonManagedReference
    @JoinColumn(name = "email", nullable = false)
    private Client client;

}
