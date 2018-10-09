package com.management.parkingmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "Price")
@Table(name = "Price")
public class Price implements Serializable {

    @Id
    @Column
    private int priceId;

    @Column
    private BigDecimal priceFor15Minutes;

    @OneToOne
    @JoinColumn(name = "parkId")
    @JsonBackReference
    private Park park;

}
