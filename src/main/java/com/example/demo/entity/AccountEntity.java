package com.example.demo.entity;

import javax.persistence.*;

@Entity
@Table(name = "accounts")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    private Double balance;

    // Constructors
    public AccountEntity() {
        this.balance = 0.0; // Default balance
    }

    public AccountEntity(Long userId, Double balance) {
        this.userId = userId;
        this.balance = balance;
    }

     public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
