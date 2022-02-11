package com.datastax.tutorials.service.user;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.cassandra.core.mapping.CassandraType;
import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user")
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 4067531918643498429L;
	
	@PrimaryKey("user_id")
	private UUID userId;
	
	@Column("user_email")
	private String userEmail;
	
	@Column("picture_url")
	private String pictureUrl;
	
	@Column("first_name")
	private String firstName;
	
	@Column("last_name")
	private String lastName;
	
	@Column("locale")
	private String locale;
	
	@CassandraType(type = CassandraType.Name.UDT, userTypeName = "address")
    @Column("address")
	private List<AddressEntity> addresses;

	@Column("password")
	private String password;
	
	@Column("tokentxt")
	private String tokentxt;
	
	@Column("password_date")
	private Date passwordDate;
	
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

	public List<AddressEntity> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<AddressEntity> addresses) {
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
	
	public Date getPasswordDate() {
		return passwordDate;
	}
	
	public void setPasswordDate(Date passwordDate) {
		this.passwordDate = passwordDate;
	}
}
