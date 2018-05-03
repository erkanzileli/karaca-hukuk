package entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "Gider")
public class Gider {

    @Id
    @Column(name = "idGider")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGider;

    @Column(name = "createDate")
    private Date createDate;

    @Column(name = "tutar")
    private double tutar;

    public Gider() {
    }

    public Gider(Date createDate, double tutar) {
        this.createDate = createDate;
        this.tutar = tutar;
    }

    public int getIdGider() {
        return idGider;
    }

    public void setIdGider(int idGider) {
        this.idGider = idGider;
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
