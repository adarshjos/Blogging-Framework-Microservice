package com.IAM.shoutOut.model.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
public class VerificationToken extends BaseEntity {
    @NotNull
    @Column(name="token")
    private String token;

    @OneToOne
    private UserInfo user;

    private LocalDateTime expiryDate;

    public VerificationToken() { }

    public String getToken() { return token; }

    public void setToken(String token) { this.token = token; }

    public UserInfo getUser() { return user; }

    public void setUser(UserInfo user) { this.user = user; }

    public LocalDateTime getExpiryDate() { return expiryDate; }

    public void setExpiryDate(LocalDateTime expiryDate) { this.expiryDate = expiryDate; }
}
