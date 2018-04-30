package entity;

import javax.persistence.*;
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

    private int idQuestionPack;

    private String type;

    private String status;

    private LocalDateTime date;

    private String description;

    private String judicalWay;

    public Lawsuit() {
    }

    public Lawsuit(int idLawyer, int idCustomer, int idOpponent, int idQuestionPack, String type, String status, LocalDateTime date, String description, String judicalWay) {
        this.idLawyer = idLawyer;
        this.idCustomer = idCustomer;
        this.idOpponent = idOpponent;
        this.idQuestionPack = idQuestionPack;
        this.type = type;
        this.status = status;
        this.date = date;
        this.description = description;
        this.judicalWay = judicalWay;
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

    public int getIdQuestionPack() {
        return idQuestionPack;
    }

    public void setIdQuestionPack(int idQuestionPack) {
        this.idQuestionPack = idQuestionPack;
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

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJudicalWay() {
        return judicalWay;
    }

    public void setJudicalWay(String judicalWay) {
        this.judicalWay = judicalWay;
    }
}
