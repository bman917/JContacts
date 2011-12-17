package com.bman917.jcontacts.models;

public class Address {
	Long id;
	String description;
	String address;
	
	public Address()
	{
		
	}
        
        public Address(String address)
	{
		this.address = address;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public boolean equals(Object o) {
		if (o instanceof Address) {
			Address add = (Address) o;
			return add.getAddress().equals(add.getAddress());
		} else {
			return false;
		}
	}
}