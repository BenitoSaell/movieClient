package com.benitosaell.client.model;

public class Profile {
	private int id;
	private String email;
	private String profile;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return "Profile [id=" + id + ", email=" + email + ", profile=" + profile + "]";
	}

	
}
