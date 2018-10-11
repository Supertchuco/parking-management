package com.management.parkingmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@Entity(name = "Client")
@Table(name = "Client")
@AllArgsConstructor
@NoArgsConstructor
public class Client implements Serializable {

    @Id
    @Column
    private String email;

    @Column
    private String password;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private Wallet wallet;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Vehicle> vehicles;
}
