package entity;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Lawsuit")
public class Lawsuit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "idLawsuit")
	private int idLawsuit;

	@Column(name = "idLawyer")
	private int idLawyer;

	@Column(name = "idCustomer")
	private int idCustomer;

	@Column(name = "idOpponent")
	private int idOpponent;

	@Column(name = "type")
	private String type;

	@Column(name = "status")
	private String status;

	@Column(name = "date")
	private Date date;

	@Column(name = "description")
	private String description;


	public Lawsuit() {
	}

    public Lawsuit(int idLawyer, int idCustomer, int idOpponent, String type, String status, Date date, String description) {
        this.idLawyer = idLawyer;
        this.idCustomer = idCustomer;
        this.idOpponent = idOpponent;
        this.type = type;
        this.status = status;
        this.date = date;
        this.description = description;
    }

	public int getIdLawsuit() {
		return idLawsuit;
	}

	public void setIdLawsuit(int idLawsuit) {
		this.idLawsuit = idLawsuit;
	}

	public int getIdLawyer() {
		return idLawyer;
	}

	public void setIdLawyer(int idLawyer) {
		this.idLawyer = idLawyer;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}

	public int getIdOpponent() {
		return idOpponent;
	}

	public void setIdOpponent(int idOpponent) {
		this.idOpponent = idOpponent;
	}

    public String getType() {
        return type;
    }

	public void setType(String type) {
		this.type = type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
