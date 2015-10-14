package com.hi5.ff.entity;

public class User {
	private String userId;
	private String password;
	private String  profileId;
	public User() {
		super();
	}
	
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public User(String profileId, String password) {
		super();
		this.profileId = profileId;
		this.password = password;
	}
	public String getProfileId() {
		return profileId;
	}
	public void setProfileId(String profileId) {
		this.profileId = profileId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
