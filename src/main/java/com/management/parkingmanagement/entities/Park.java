package com.management.parkingmanagement.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "Park")
@Table(name = "Park")
@NoArgsConstructor
public class Park implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private int parkId;

    @Column
    private String parkName;

    @Column
    private String address;

    @Column
    private boolean active;

    @OneToOne
    @JoinColumn(name = "priceId")
    private Price price;
}
