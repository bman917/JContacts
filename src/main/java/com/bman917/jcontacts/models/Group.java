package com.bman917.jcontacts.models;

import java.util.Set;

public class Group {
	int id;
	String name;
	String description;
	Set<ContactEntry> contactEntries;
	public Group()
	{
		
	}
	
	public Group(String name)
	{
		this.name = name;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
	public Set<ContactEntry> getContactEntries() {
		return contactEntries;
	}

	public void setContactEntries(Set<ContactEntry> contactEntries) {
		this.contactEntries = contactEntries;
	}

	public String toString()
	{
		return getName();
	}
}
