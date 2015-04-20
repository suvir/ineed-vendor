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
	String name;

	@Column(name = "VENDOR_CITY")
	String city;
	
	@Column(name = "VENDOR_LATITUDE")
	String latitude;
	
	@Column(name = "VENDOR_LONGITUDE")
	String longitude;
	
	@Column(name = "VENDOR_PHONE")
	String phone;
	
	
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
}
