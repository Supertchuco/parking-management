package com.management.parkingmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "Client")
@Table(name = "Client")
@AllArgsConstructor
public class Client implements Serializable {

    @Id
    @Column
    private String email;

    @Column
    private String password;

    @OneToOne(mappedBy = "wallet", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Wallet wallet;

    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private List<Vehicle> vehicles;
}
