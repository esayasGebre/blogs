package ea.blog.model;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	private String city;
	private String state;
	private String country;
	private String phone;
	private String email;
	
	public Address(){}
	
	

	public Address(String city, String state, String country, String phone, String email) {
		super();
		this.city = city;
		this.state = state;
		this.country = country;
		this.phone = phone;
		this.email = email;
	}



	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	
}
