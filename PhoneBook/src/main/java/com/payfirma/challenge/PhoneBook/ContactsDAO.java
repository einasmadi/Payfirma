package com.payfirma.challenge.PhoneBook;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Sorts;

import static com.mongodb.client.model.Filters.*;

public class ContactsDAO {

	private final MongoCollection<Document> contactsCollection;

	public ContactsDAO(MongoCollection<Document> contactsCollection) {
		this.contactsCollection = contactsCollection;
	}

	public boolean addContact(String name, String occupation, String phone){
		
		try{
			contactsCollection.insertOne(
				new Document("name", name)
				.append("occupation", occupation)
				.append("phone", phone));
			
			return true;
		} catch (Exception e){
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Document> findContact(String name, String occupation, String phone) {
		return contactsCollection
				//Ok to search by all, if a field is blank, it is ignored
				.find(and(regex("name", name, "i"), regex("occupation", occupation, "i"), regex("phone", phone, "i")))
				.sort(Sorts.ascending("name"))
				.into(new ArrayList<Document>());
	}
	
	
	public boolean deleteContact(String name, String occupation, String phone) {

		try {
			contactsCollection.deleteOne(
					new Document("name", name)
					.append("occupation", occupation)
					.append("phone", phone));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//Can be better refined by sending in an old document and a new document
	public boolean updateContact(String oldName, String oldOccupation, String oldPhone,
								 String newName, String newOccupation, String newPhone) {
		
		try {
			contactsCollection.updateOne(
					new Document("name", oldName)
					.append("occupation", oldOccupation)
					.append("phone", oldPhone),
					new Document("name", newName)
					.append("occupation", newOccupation)
					.append("phone", newPhone));
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
