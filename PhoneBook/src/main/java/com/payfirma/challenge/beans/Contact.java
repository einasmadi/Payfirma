package com.payfirma.challenge.beans;

public class Contact {

	private String firstName;
	private String lastName;
	//People can have multiple mobilePhoneNumbers so this is best represented as a list
	//However, for brevity's sake - we will assume everyone only has one phone number
	private String mobilePhoneNumber;
	// String homePhoneNumber;
	// String faxNumber;
	// String address;
	// Further attributes can be added if necessary

	public Contact(String firstName, String lastName, String phoneNumber) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobilePhoneNumber = phoneNumber;
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

	public String getMobilePhoneNumber() {
		return mobilePhoneNumber;
	}

	public void setMobilePhoneNumber(String phoneNumber) {
		this.mobilePhoneNumber = phoneNumber;
	}

	@Override
	public String toString() {
		return "Contacts [" 
				+ "firstName=" + firstName 
				+ ", lastName=" + lastName 
				+ ", phoneNumber=" + mobilePhoneNumber
				+ "]";
	}

}
