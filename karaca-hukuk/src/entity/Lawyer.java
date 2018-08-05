package entity;

import javax.persistence.*;

@Entity
@Table(name = "Lawyer")
public class Lawyer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLawyer;

    private int idMember;

    private String lawyerNumber;

    public Lawyer() {
    }

    public Lawyer(int idMember, String lawyerNumber) {
        this.idMember = idMember;
        this.lawyerNumber = lawyerNumber;
    }

    public int getIdLawyer() {
        return idLawyer;
    }

    public void setIdLawyer(int idLawyer) {
        this.idLawyer = idLawyer;
    }

    public int getIdMember() {
        return idMember;
    }

    public void setIdMember(int idMember) {
        this.idMember = idMember;
    }

    public String getLawyerNumber() {
        return lawyerNumber;
    }

    public void setLawyerNumber(String lawyerNumber) {
        this.lawyerNumber = lawyerNumber;
    }
}
