package entity;

import javax.persistence.*;

@Entity
@Table(name = "ilceler")
public class District {

    @Id
    @Column(name = "ilce_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "il_no")
    private int il_no;

    @Column(name = "isim")
    private String isim;

    public District() {
    }

    public District(int il_no, String isim) {
        this.il_no = il_no;
        this.isim = isim;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIl_no() {
        return il_no;
    }

    public void setIl_no(int il_no) {
        this.il_no = il_no;
    }

    public String getIsim() {
        return isim;
    }

    public void setIsim(String isim) {
        this.isim = isim;
    }
}
