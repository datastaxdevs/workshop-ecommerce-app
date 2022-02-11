package com.datastax.tutorials.service.user;

import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.UserDefinedType;

@UserDefinedType("address")
public class AddressEntity implements Serializable {

	private static final long serialVersionUID = 1817053316281666003L;

	private String mailtoName;
	
	private String street;
	
	private String street2;
	
	private String city;
	
	private String stateProvince;
	
	private String postalCode;
	
	private String country;
	
	private String type;
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getMailtoName() {
		return mailtoName;
	}
	
	public void setMailtoName(String mailtoName) {
		this.mailtoName = mailtoName;
	}
	
	public String getStreet() {
		return street;
	}
	
	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getStreet2() {
		return street2;
	}
	
	public void setStreet2(String street2) {
		this.street2 = street2;
	}
	
	public String getCity() {
		return city;
	}
	
	public void setCity(String city) {
		this.city = city;
	}
	
	public String getStateProvince() {
		return stateProvince;
	}
	
	public void setStateProvince(String stateProvince) {
		this.stateProvince = stateProvince;
	}
	
	public String getPostalCode() {
		return postalCode;
	}
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	
	public String getCountry() {
		return country;
	}
	
	public void setCountry(String country) {
		this.country = country;
	}

}
