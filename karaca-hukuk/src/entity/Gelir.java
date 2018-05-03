package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Gelir")
public class Gelir {

    @Id
    @Column(name = "idGelir")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGelir;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "tutar")
    private Double tutar;

    public Gelir() {
    }

    public Gelir(Date createDate, double tutar) {
        this.createDate = createDate;
        this.tutar = tutar;
    }

    public int getIdGelir() {
        return idGelir;
    }

    public void setIdGelir(int idGelir) {
        this.idGelir = idGelir;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public double getTutar() {
        return tutar;
    }

    public void setTutar(double tutar) {
        this.tutar = tutar;
    }
}
