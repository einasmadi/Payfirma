package com.payfirma.challenge.beans;

import java.util.Map;
import java.util.TreeMap;

public class PhoneBook {
	
	//A PhoneBook is a list of contacts, we will be using the mobilePhoneNumber, since they are unique, as the key
	private Map<String, Contact> contacts;

	public PhoneBook() {
		contacts = new TreeMap<>();
		
		//Create dummy contacts
		Contact einasMadi = new Contact("Einas", "Madi", "6479897395");
		Contact mohsenMadi = new Contact("Mohsen", "Madi", "4162929292");
		Contact nahedSoleman = new Contact("Nahed", "Soleman", "6478989898");
		
		contacts.put(einasMadi.getMobilePhoneNumber(), einasMadi);
		contacts.put(mohsenMadi.getMobilePhoneNumber(), mohsenMadi);
		contacts.put(nahedSoleman.getMobilePhoneNumber(), nahedSoleman);
		
	}
	
	public boolean addContact(Contact contact){
		if(contacts.containsKey(contact.getMobilePhoneNumber())){
			//Contact already exists
			return false;
		}
		contacts.put(contact.getMobilePhoneNumber(), contact);
		return true;
	}
	
	public boolean removeContact(String mobilePhoneNumer){
		if(contacts.containsKey(mobilePhoneNumer)){
			contacts.remove(mobilePhoneNumer);
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		return "PhoneBook [contacts=" + contacts + "]";
	}	
}
