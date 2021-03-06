package example.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "vendor")
/*
 * define O-R mapping of todolist table
 */
public class Vendor {
	@Id //primary key
	@Column(name = "VENDOR_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Basic
	@Column(name = "VENDOR_NAME")
	private
	String name;
	
	@Column(name = "VENDOR_DESCRIPTION")
	private
	String description;
	
	@Column(name = "VENDOR_EMAILID")
	private 
	String email_id;
	
	@Column(name = "VENDOR_PASSWORD")
	private
	String password;
	
	@Column(name = "VENDOR_TYPE")
	private
	VendorType vendor_type;
	
	@Column(name = "VENDOR_ADDRESS")
	private
	String address;	
	
	@Column(name = "VENDOR_LATITUDE")
	private
	String latitude;
	
	@Column(name = "VENDOR_LONGITUDE")
	private
	String longitude;
	
	@Column(name = "VENDOR_PHONE")
	String phone;
	
	@Column(name = "VENDOR_STATE")
	private
	String state;
	
	@Column(name = "VENDOR_CITY")
	private
	String city;
	
	//Id
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	//Name
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	//City
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
	
	//Latitude
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	
	//Longitude
	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	
	//Phone
	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	

	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"name\": \"%s\", \"city\": \"%s\", \"latitude\": \"%s\", \"longitude\": \"%s\", \"phone\": \"%s\"}", id, name, city, latitude, longitude, phone);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getEmail_id() {
		return email_id;
	}

	public void setEmail_id(String email_id) {
		this.email_id = email_id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public VendorType getVendor_type() {
		return vendor_type;
	}

	public void setVendor_type(VendorType vendor_type) {
		this.vendor_type = vendor_type;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
