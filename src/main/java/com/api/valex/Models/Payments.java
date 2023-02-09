package com.api.valex.Models;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "payments")
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Timestamp timestamp;

    private Float amount;
    @ManyToOne
    @JoinColumn(name="card_id")
    private Cards card;

    @ManyToOne
    @JoinColumn(name = "businesses_id")
    private Businesses business;

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

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Cards getCard() {
        return card;
    }

    public void setCard(Cards card) {
        this.card = card;
    }

    public Businesses getBusiness() {
        return business;
    }

    public void setBusiness(Businesses business) {
        this.business = business;
    }
}
