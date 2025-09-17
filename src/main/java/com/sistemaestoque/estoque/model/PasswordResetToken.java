package com.sistemaestoque.estoque.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_tokens")
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @OneToOne(targetEntity = com.sistemaestoque.estoque.user.User.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private com.sistemaestoque.estoque.user.User user;

    private LocalDateTime expiryDate;

    // Construtores
    public PasswordResetToken() {}

    public PasswordResetToken(String token, com.sistemaestoque.estoque.user.User user) {
        this.token = token;
        this.user = user;
        this.expiryDate = LocalDateTime.now().plusHours(1); // Token válido por 1 hora
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public com.sistemaestoque.estoque.user.User getUser() {
        return user;
    }

    public void setUser(com.sistemaestoque.estoque.user.User user) {
        this.user = user;
    }

    public LocalDateTime getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDateTime expiryDate) {
        this.expiryDate = expiryDate;
    }
}