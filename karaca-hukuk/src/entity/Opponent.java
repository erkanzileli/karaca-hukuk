package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Opponent")
public class Opponent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idOpponent;

    private int idLawsuit;

    private String name;

    private String surname;

    private String type;

    private LocalDate createDate;

    public Opponent() {
    }

    public Opponent(int idLawsuit, String name, String surname, String type, LocalDate createDate) {
        this.idLawsuit = idLawsuit;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.createDate = createDate;
    }

    public int getIdOpponent() {
        return idOpponent;
    }

    public void setIdOpponent(int idOpponent) {
        this.idOpponent = idOpponent;
    }

    public int getIdLawsuit() {
        return idLawsuit;
    }

    public void setIdLawsuit(int idLawsuit) {
        this.idLawsuit = idLawsuit;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }
}
