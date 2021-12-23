package com.blockchain_backend.Entities;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class Block {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id ;

    private Date creation_date;
    private String prev_hashCode;
    @OneToOne
    private Block prevBlock;
    private int nonce;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Transaction> transactions  = new ArrayList<>();

    private String hashCode;

    public Block(){
        this.prevBlock = null ;
    }
    public Block(Long id, Date creation_date, String prev_hashCode, int nonce, List<Transaction> transactions) {
        this.id = id;
        this.creation_date = creation_date;
        this.prev_hashCode = prev_hashCode;
        this.nonce = nonce;
        this.transactions = transactions;
    }

    @Override
    public String toString() {
        return "Block{" +
                "id=" + id +
                ", creation_date=" + creation_date +
                ", prev_hashCode='" + prev_hashCode + '\'' +
                ", nonce=" + nonce +
                ", transactions=" + transactions +
                '}';
    }
}
