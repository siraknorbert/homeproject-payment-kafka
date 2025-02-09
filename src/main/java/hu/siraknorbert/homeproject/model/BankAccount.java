package hu.siraknorbert.homeproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Inheritance
@Table(name = "bank_account")
public class BankAccount extends BaseEntity {

    @Column(name = "owner_name", nullable = false, length = 100)
    private String ownerName;

    @Column(name = "owner_email", nullable = false, length = 100)
    private String ownerEmail;

    @Column(name = "balance", nullable = false, precision = 10, scale = 2)
    private BigDecimal balance;

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String name) {
        this.ownerName = name;
    }

    public String getOwnerEmail() {
        return ownerEmail;
    }

    public void setOwnerEmail(String email) {
        this.ownerEmail = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
