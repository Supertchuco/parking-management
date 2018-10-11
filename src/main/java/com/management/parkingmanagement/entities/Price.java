package com.management.parkingmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "Price")
@Table(name = "Price")
@AllArgsConstructor
@NoArgsConstructor
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
