package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Installment")
public class Installment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstallent;

    private int idInvoice;

    private double amount;

    private double remainingAmount;

    private String status;

    private LocalDateTime date;

    public Installment() {
    }

    public Installment(int idInvoice, double amount, double remainingAmount, String status, LocalDateTime date) {
        this.idInvoice = idInvoice;
        this.amount = amount;
        this.remainingAmount = remainingAmount;
        this.status = status;
        this.date = date;
    }

    public int getIdInstallent() {
        return idInstallent;
    }

    public void setIdInstallent(int idInstallent) {
        this.idInstallent = idInstallent;
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getRemainingAmount() {
        return remainingAmount;
    }

    public void setRemainingAmount(double remainingAmount) {
        this.remainingAmount = remainingAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
