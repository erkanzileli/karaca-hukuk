package entity;

import com.sun.istack.internal.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCustomer")
    private int idCustomer;

    private long tc;

    private long taxNumber;

    private String type;

    private String name;

    private String surname;

    private long phoneNumber;

    private String gender;

    private String isRemoved;

    public Customer() {
    }

    public Customer(long tc, String type, String name, String surname, long phoneNumber,
                    String gender) {
        this.tc = tc;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Customer(long taxNumber, String type, String name, String surname, long phoneNumber) {
        this.taxNumber = taxNumber;
        this.type = type;
        this.name = name;
        this.surname = surname;
        this.phoneNumber = phoneNumber;
    }

    public int getIdCustomer() {
        return idCustomer;
    }

    public void setIdCustomer(int idCustomer) {
        this.idCustomer = idCustomer;
    }

    public long getTc() {
        return tc;
    }

    public void setTc(long tc) {
        this.tc = tc;
    }

    public long getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(long taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsRemoved() {
        return isRemoved;
    }

    public void setIsRemoved(String isRemoved) {
        this.isRemoved = isRemoved;
    }
}
