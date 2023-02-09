package com.api.valex.Models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "recharges")
public class Recharges {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp timestamp;

    private Long amount;

    @ManyToOne
    @JoinColumn(name="card_id")
    private Cards card;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Timestamp timestamp) {
        this.timestamp = timestamp;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Cards getCard() {
        return card;
    }

    public void setCard(Cards card) {
        this.card = card;
    }
}
