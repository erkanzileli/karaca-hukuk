package entity;

import javax.persistence.*;

@Entity
@Table(name = "Member")
public class Member {

    @Id
    @Column(name = "idMember")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMember;

    @Column(name = "tc")
    private long tc;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "type")
    private String type;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phoneNumber")
    private long phoneNumber;

    public Member() {
    }

    public Member(long tc, String name, String surname, String type, String password, String email, String gender, long phoneNumber) {
        this.tc = tc;
        this.name = name;
        this.surname = surname;
        this.type = type;
        this.password = password;
        this.email = email;
        this.gender = gender;
        this.phoneNumber = phoneNumber;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public long getTc() {
        return tc;
    }

    public void setTc(long tc) {
        this.tc = tc;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
