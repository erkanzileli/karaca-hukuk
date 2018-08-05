package model;

import javax.persistence.Entity;
import java.sql.Date;

public class LawsuitListModel {
    private String name;
    private String surname;
    private long tc;
    private long phoneNumber;
    private String lawsuitType;
    private String lawsuitStatus;
    private String customerType;
    private Date createDate;

    public LawsuitListModel(){

    }

    public LawsuitListModel(String name, String surname, long tc, long phoneNumber, String lawsuitType, String lawsuitStatus, String customerType, Date createDate) {
        this.name = name;
        this.surname = surname;
        this.tc = tc;
        this.phoneNumber = phoneNumber;
        this.lawsuitType = lawsuitType;
        this.lawsuitStatus = lawsuitStatus;
        this.customerType = customerType;
        this.createDate = createDate;
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

    public long getTc() {
        return tc;
    }

    public void setTc(long tc) {
        this.tc = tc;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getLawsuitType() {
        return lawsuitType;
    }

    public void setLawsuitType(String lawsuitType) {
        this.lawsuitType = lawsuitType;
    }

    public String getLawsuitStatus() {
        return lawsuitStatus;
    }

    public void setLawsuitStatus(String lawsuitStatus) {
        this.lawsuitStatus = lawsuitStatus;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
   //  SELECT name,surname,Customer.type as customerType,tc,phoneNumber,L.type as lawsuitType,status,date from Customer inner join Lawsuit L ON Customer.idCustomer = L.idCustomer