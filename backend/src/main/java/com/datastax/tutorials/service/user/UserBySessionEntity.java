package com.datastax.tutorials.service.user;

import java.util.UUID;
import java.io.Serializable;

import org.springframework.data.cassandra.core.mapping.Column;
import org.springframework.data.cassandra.core.mapping.PrimaryKey;
import org.springframework.data.cassandra.core.mapping.Table;

@Table("user_by_session")
public class UserBySessionEntity implements Serializable {

	private static final long serialVersionUID = -2879265695757656335L;

	@PrimaryKey("user_session_id")
	private String userSessionId;

	@Column("user_id")
	private UUID userId;
	
	public UUID getUserId() {
		return userId;
	}

	public void setUserId(UUID userId) {
		this.userId = userId;
	}

	public String getUserSessionId() {
		return userSessionId;
	}

	public void setUserSessionId(String userSessionId) {
		this.userSessionId = userSessionId;
	}
}
