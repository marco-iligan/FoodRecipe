package com.entity;

public class Profile {

	private long profileId;
	private String firstName;
	private String middleName;
	private String lastName;
	
	public Profile() {
		
	}
	
	 public Profile(long profileId, String firstName, String midName, String lastName) {
	        this.profileId = profileId;
	        this.firstName = firstName;
	        this.middleName = midName;
	        this.lastName = lastName;
	    }
	 
	 public Profile(String firstName, String midName, String lastName) {
	        this.firstName = firstName;
	        this.middleName = midName;
	        this.lastName = lastName;
	    }
	
	public long getProfileId() {
        return profileId;
    }

    public void setProfileId(long profileId) {
        this.profileId = profileId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMidName() {
        return middleName;
    }

    public void setMidName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
	
}
