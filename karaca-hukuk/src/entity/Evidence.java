package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Evidence")
public class Evidence {

    @Column(name = "idEvidence")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvidence;

    @Column(name = "idLawsuit")
    private int idLawsuit;

    @Column(name = "atype")
    private String atype;

    @Column(name = "adesc")
    private String adesc;

    @Column(name = "fromWho")
    private String fromWho;

    @Column(name = "createDate")
    private Date adate;

    public Evidence() {
    }

    public Evidence(int idLawsuit, String fromWho, String atype, String adesc, Date adate) {
        this.idLawsuit = idLawsuit;
        this.fromWho = fromWho;
        this.adate = adate;
        this.adesc = adesc;
        this.atype = atype;
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

    public String getAtype() {
        return atype;
    }

    public void setAtype(String atype) {
        this.atype = atype;
    }

    public String getAdesc() {
        return adesc;
    }

    public void setAdesc(String adesc) {
        this.adesc = adesc;
    }

    public void setFromWho(String fromWho) {
        this.fromWho = fromWho;
    }

    public Date getAdate() {
        return adate;
    }

    public void setAdate(Date adate) {
        this.adate = adate;
    }
}
