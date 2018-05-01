package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Lawsuit")
public class Lawsuit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLawsuit;

    private int idLawyer;

    private int idCustomer;

    private int idOpponent;


    private String type;

    private String status;

    private LocalDate date;

    private String description;


    public Lawsuit() {
    }

    public Lawsuit(int idLawyer, int idCustomer, int idOpponent, String type, String status, LocalDate date, String description) {
        this.idLawyer = idLawyer;
        this.idCustomer = idCustomer;
        this.idOpponent = idOpponent;
        this.type = type;
        this.status = status;
        this.date = date;
        this.description = description;
    }

    public int getIdLawsuit() {
        return idLawsuit;
    }

    public void setIdLawsuit(int idLawsuit) {
        this.idLawsuit = idLawsuit;
    }

    public int getIdLawyer() {
        return idLawyer;
    }

    public void setIdLawyer(int idLawyer) {
        this.idLawyer = idLawyer;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public int getIdOpponent() {
        return idOpponent;
    }

    public void setIdOpponent(int idOpponent) {
        this.idOpponent = idOpponent;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
