package com.blockchain_backend.Entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor

public class BlockChain {

    @Id
    private String id ;
    private String nom ;
    private int difficulty ;
    private double rewards ;

    public BlockChain(String id, String nom, int difficulty, double rewards) {
        this.id = id;
        this.nom = nom;
        this.difficulty = difficulty;
        this.rewards = rewards;
    }

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Block lastBlock ;
}
