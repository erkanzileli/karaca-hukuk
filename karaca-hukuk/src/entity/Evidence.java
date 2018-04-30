package entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "Evidence")
public class Evidence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvidence;

    private int idLawsuit;

    private String fromWho;

    private LocalDateTime date;

    public Evidence() {
    }

    public Evidence(int idLawsuit, String fromWho, LocalDateTime date) {
        this.idLawsuit = idLawsuit;
        this.fromWho = fromWho;
        this.date = date;
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

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
