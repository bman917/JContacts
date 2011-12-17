package com.bman917.jcontacts.models;

public class Email {
	
	Long id;
	String description;
	String email;
        public Email (){
            
        }
        public Email(String email) {
            this.setEmail(email);
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

}
