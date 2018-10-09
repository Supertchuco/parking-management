package com.management.parkingmanagement.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
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

}
