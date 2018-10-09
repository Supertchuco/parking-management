package com.management.parkingmanagement.entities;

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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "walletId")
    private Wallet wallet;

    @OneToMany(
            mappedBy = "client",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Vehicle> vehicles;
}
