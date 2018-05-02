package entity;

import javax.persistence.*;

@Entity
@Table(name = "Adress")
public class Adress {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idAdress")
	private int idAdress;

	private int idCustomer;

	@Column(name = "city")
	private String city;

	@Column(name = "county")
	private String county;

	@Column(name = "street")
	private String street;

	@Column(name = "postalCode")
	private int postalCode;

	@Column(name = "phoneNumber")
	private long phoneNumber;

	@Column(name = "doorNumber")
	private String doorNumber;

	@Column(name = "type")
	private String type;

	public Adress() {
	}

	public Adress(String city, String county, String street, int postalCode, long phoneNumber,
			String doorNumber, String type, int idCustomer) {
		this.city = city;
		this.county = county;
		this.street = street;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.doorNumber = doorNumber;
		this.type = type;
		this.idCustomer = idCustomer;
	}

	public int getIdAdress() {
		return idAdress;
	}

	public void setIdAdress(int idAdress) {
		this.idAdress = idAdress;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCounty() {
		return county;
	}

	public void setCounty(String county) {
		this.county = county;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDoorNumber() {
		return doorNumber;
	}

	public void setDoorNumber(String doorNumber) {
		this.doorNumber = doorNumber;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getIdCustomer() {
		return idCustomer;
	}

	public void setIdCustomer(int idCustomer) {
		this.idCustomer = idCustomer;
	}
}
