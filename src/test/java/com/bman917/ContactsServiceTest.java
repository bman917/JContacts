package com.bman917;

import com.bman917.jcontacts.JContactService;
import com.bman917.jcontacts.JContactsFactory;
import java.util.List;

import junit.framework.TestCase;

import com.bman917.jcontacts.models.ContactEntry;

public class ContactsServiceTest extends TestCase {

	public void testBasicCreate() throws Exception 
        {
		JContactService sv = JContactsFactory.getInstance();
                
		sv.deleteAllContacts();
		
		/*
		 * Create Contact
		 */
		ContactEntry contact = new ContactEntry();
		contact.setFirstName("Jacky");
		contact.setLastName("Chan");
		contact.setMiddleName("Trinidad");

		System.out.println("Contact ID before saving: " + contact.getId());
		long id = sv.saveContact(contact);
		System.out.println("Contact ID after saving: " + contact.getId());
                
                /*
		 * Add ContactNumber
		 */
                contact.addNumber("759-4150", "HOME PHONE");
                contact.addNumber("844-4477", "OFFICE PHONE");
		
		/*
		 * Add Email
		 */
		contact.addEmail("email@email.com");
                
                /*
		 * Add Address
		 */
                contact.addAddress("Room 2306, Rada Reagency Condo, Rada Street, Makati", "HOME");
                sv.updateContact(contact);
                
                contact.replaceEmailAddress("first@email.com, second@email.com");
                sv.updateContact(contact);
                
                /*
                 * Test adding individual details
                 */
                sv.addAddress("Room 2306, Rada Reagency Condo, Rada Street, Makati", "HOME", id);
                sv.addContactNumber("759-4150", "HOME PHONE", id);
		sv.addContactNumber("844-4477", "OFFICE PHONE", id);

                
                List<ContactEntry> all = sv.getAllContacts();
		assertEquals("getAllContacts should return 1", 1, all.size());
                
                ContactEntry ctmp = all.get(0) ;
                assertEquals("Contact should have 1 email address", 2 , ctmp.getEmailAddress().size());
                
                /*
		 * Assign the contact to a Group and check that tha association is made.
		 */
		sv.assignGroup("EMPLOYEE", contact.getId());

		List<ContactEntry> list = sv.findContact("Jacky", "Chan");
		assertEquals("Find Contact should return 1 record.", list.size(), 1);
		assertEquals("Contact should have four Contact Numbers", 4, list.get(0).getContactNumbers().size());
                assertEquals("Contact should have two Addresses", 2, list.get(0).getAddress().size());
		assertEquals("Contact should belong to a group", 1, list.get(0).getGroups().size());
		assertEquals("Contact group should be EMPLOYEE", "EMPLOYEE", list.get(0).getGroups().iterator().next().getName());
		assertEquals("Contact should have 1 email address", 2 , list.get(0).getEmailAddress().size());
		/*
		 * Delete the group. The contact should be dis-associated to the group.
		 */
		sv.deleteGroup("EMPLOYEE");
		List<ContactEntry> list2 = sv.findContact("Jacky", "Chan");
		assertEquals("Since the group has been deleted. The Contact should no longer be assigned to any group.", list2.size(), 0);
        }
	
	/**
	 * Test that duplicate records of selected models are not permitted
	 */
	public void testAddingDuplicates() throws Exception
	{
		JContactService sv = JContactsFactory.getInstance();
		sv.deleteAllGroups();
		
		//Group
		String name1 = "GroupOne";
		sv.createGroup(name1, "Test Only. This is the description of GroupOne. Add special charcters %^!@#$%^&*()");
		sv.createGroup("GroupTwo", "Group Two description.");
		try
		{
			sv.createGroup(name1, "Another Description");
			fail("There should have been an exception when creating a group that already exist in the database.");
		}
		catch(Exception e)
		{
			System.out.println("\n\nTest Passed. This is not a mistake. Got EXPECTED exception:" + e);
		}
	}
}