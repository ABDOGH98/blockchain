package com.blockchain_backend.Entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class Transaction {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;
    private Date date;
    private String address_src ;
    private String address_dst ;
    private double montant ;
}
