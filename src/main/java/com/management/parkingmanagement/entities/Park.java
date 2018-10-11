package com.management.parkingmanagement.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity(name = "Park")
@Table(name = "Park")
@AllArgsConstructor
@NoArgsConstructor
public class Park implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private int id;

    @Column
    private String parkName;

    @Column
    private String address;

    @Column
    private boolean active;

    @OneToOne(mappedBy = "park", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Price price;
}
