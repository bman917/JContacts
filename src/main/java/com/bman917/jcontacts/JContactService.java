package com.bman917.jcontacts;

import java.util.List;

import com.bman917.jcontacts.models.ContactEntry;
import com.bman917.jcontacts.models.Group;

public interface JContactService {
	
	public long saveContact(ContactEntry contact);
	
	public void updateContact(ContactEntry contact);
	
	public void deleteContact(ContactEntry contact);
        
        public void deleteContact(List<ContactEntry> contacts);
	
	public List<ContactEntry> findContact(String firstName, String lastName);
	
	public List<ContactEntry> findContact(String firstName, String lastName, String middleName);
	
	public List<ContactEntry> findContactByEmail(String emailAddress);
	
	public List<ContactEntry> findContact(Group group);
	
	public void addContactNumber(String number, String description, long contactID);
	
	public void addAddress(String address, String description, long contactID);
        
        public void deleteAllContacts();
        
        public List<ContactEntry> getAllContacts();
        
        public Group assignGroup(String name, long contactID);
        
        public void deleteGroup(String name);
        
        public void deleteAllGroups();
        
        public Group createGroup(String name, String description);
        
        public void updateContactEntry(ContactEntry contact);
}
