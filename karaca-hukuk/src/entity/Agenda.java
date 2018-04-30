package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Agenda")
public class Agenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idAgenda;

    private int idMember;

    private String header;

    private String description;

    private LocalDateTime date;

    public Agenda() {
    }

    public Agenda(int idMember, String header, String description, LocalDateTime date) {
        this.idMember = idMember;
        this.header = header;
        this.description = description;
        this.date = date;
    }

    public int getIdAgenda() {
        return idAgenda;
    }

    public void setIdAgenda(int idAgenda) {
        this.idAgenda = idAgenda;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
