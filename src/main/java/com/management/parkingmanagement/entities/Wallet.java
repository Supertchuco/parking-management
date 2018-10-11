package com.management.parkingmanagement.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@Entity(name = "Wallet")
@Table(name = "Wallet")
@AllArgsConstructor
@NoArgsConstructor
public class Wallet implements Serializable {

    @Id
    @GeneratedValue
    @Column
    private int walletId;

    @Column
    private BigDecimal balanceAccount;


    @OneToOne
    @JoinColumn(name = "email")
    @JsonBackReference
    private Client client;

}
