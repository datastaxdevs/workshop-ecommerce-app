package com.datastax.tutorials.service.user;

import java.util.UUID;
import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user_by_email")
public class UserByEmailEntity implements Serializable {

	private static final long serialVersionUID = -5194633590859436467L;

	@PrimaryKey("user_email")
	private String userEmail;

	@Column("user_id")
	private UUID userId;
	
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

}
