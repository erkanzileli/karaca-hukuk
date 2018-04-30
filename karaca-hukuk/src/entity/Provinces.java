package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ilceler")
public class Provinces {

    @Id
    @Column(name = "ilce_no")
    private int id;

    @Column(name = "il_no")
    private int provinceId;

    @Column(name = "ilce_isim")
    private String name;

    @Column(name = "il_isim")
    private String provinceName;

    public Provinces() {
    }

    public Provinces(int id, int provinceId, String name, String provinceName) {
        this.id = id;
        this.provinceId = provinceId;
        this.name = name;
        this.provinceName = provinceName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(int provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
