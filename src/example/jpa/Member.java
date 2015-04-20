package example.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "member")
/*
 * define O-R mapping of todolist table
 */
public class Member {
	@Id //primary key
	@Column(name = "MEMBER_ID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	long id;
	
	@Basic
	@Column(name = "MEMBER_FIRST_NAME")
	String first_name;

	@Column(name = "MEMBER_LAST_NAME")
	String last_name;
	
	@Column(name = "MEMBER_CITY")
	String city;
	
	@Column(name = "MEMBER_PHONE")
	String phone;
	
	@Column(name = "MEMBER_EMAIL")
	String email;

	
	
		//Id
		public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
		//First Name
		public String getFirstName() {
			return first_name;
		}

		public void setFirstName(String first_name) {
			this.first_name = first_name;
		}
		
		//Last Name
		public String getLastName() {
			return last_name;
		}

		public void setLastName(String last_name) {
			this.last_name = last_name;
		}
		
		//City
		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}
		
		
		//Phone
		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}
		
		//Email
		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}
	
	

	@Override
	public String toString() {
		return String.format("{\"id\": \"%d\", \"first_name\": \"%s\", \"last_name\": \"%s\", \"city\": \"%s\", \"phone\": \"%s\", \"email\": \"%s\"}", id, first_name, last_name, city, phone, email);
	}
}
