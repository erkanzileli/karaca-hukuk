package entity;

import javax.persistence.*;

@Entity
@Table(name = "iller")
public class Province {

    @Id
    @Column(name = "il_no")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int il_no;

    @Column(name = "isim")
    private String isim;

    public Province() {
    }

    public Province(String isim) {
        this.isim = isim;
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
