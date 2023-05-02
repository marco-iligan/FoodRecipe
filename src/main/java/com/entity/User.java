package com.entity;

public class User {
	
	private long userId;
	private Profile profile;
	private String username;
	private String password;
	
	 public User(long userId, Profile profile, String username, String password) {
	        this.userId = userId;
	        this.profile = profile;
	        this.username = username;
	        this.password = password;
	    }
	 
	 public User(Profile profile, String username, String password) {
	        this.profile = profile;
	        this.username = username;
	        this.password = password;
	    }
	
	public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


}
