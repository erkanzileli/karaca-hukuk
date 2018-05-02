package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "Evidence")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvidence;

    private int idLawsuit;

    private String type;

    private String desc;

    private String fromWho;

    private LocalDate date;

    public Evidence() {
    }

    public Evidence(int idLawsuit, String fromWho, String type, String desc, LocalDate date) {
        this.idLawsuit = idLawsuit;
        this.fromWho = fromWho;
        this.date = date;
        this.desc = desc;
        this.type = type;
    }

    public int getIdEvidence() {
        return idEvidence;
    }

    public void setIdEvidence(int idEvidence) {
        this.idEvidence = idEvidence;
    }

    public int getIdLawsuit() {
        return idLawsuit;
    }

    public void setIdLawsuit(int idLawsuit) {
        this.idLawsuit = idLawsuit;
    }

    public String getFromWho() {
        return fromWho;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
