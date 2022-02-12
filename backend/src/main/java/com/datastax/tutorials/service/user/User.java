package com.datastax.tutorials.service.user;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Interacting with Users.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
	@JsonProperty("user_id")
	private UUID userId;

	@JsonProperty("user_email")
	private String userEmail;

	@JsonProperty("picture_url")
	private String pictureUrl;
	
	@JsonProperty("first_name")
	private String firstName;

	@JsonProperty("last_name")
	private String lastName;

	private String locale;
	
	private List<Address> addresses;

	private String password;
	
	private String tokentxt;
	
	@JsonProperty("password_timestamp")
	private Date passwordTimestamp;
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLocale() {
		return locale;
	}

	public void setLocale(String locale) {
		this.locale = locale;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTokentxt() {
		return tokentxt;
	}

	public void setTokentxt(String tokentxt) {
		this.tokentxt = tokentxt;
	}

	public Date getPasswordTimestamp() {
		return passwordTimestamp;
	}
	
	public void setPasswordTimestamp(Date passwordTimestamp) {
		this.passwordTimestamp = passwordTimestamp;
	}
}
