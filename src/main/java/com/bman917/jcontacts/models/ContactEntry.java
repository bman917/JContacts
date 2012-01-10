package com.bman917.jcontacts.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ContactEntry {

    private Long id;
    String lastName;
    String firstName;
    String middleName;
    Date birthDay;
    Set<Email> emailAddress = new HashSet<Email>();
    Set<Address> address = new HashSet<Address>();
    Set<ContactNumber> contactNumbers = new HashSet<ContactNumber>();
    List<String> notes = new ArrayList<String>();
    Set<Group> groups = new HashSet<Group>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public Date getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public Set<Email> getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(Set<Email> emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void replaceEmailAddress(String commaDelimitedString) {
        replace(emailAddress, commaDelimitedString, Email.class);
    }

    public void replaceContactNumber(String commaDelimitedString) {
        replace(contactNumbers, commaDelimitedString, ContactNumber.class);
    }

    public void replaceAddress(String commaDelimitedString) {
        replace(address, commaDelimitedString, Address.class);
    }

    private void replace(Set set, String commaDelimitedString, Class type) {
        if (commaDelimitedString != null) {
            String[] emails = commaDelimitedString.trim().split(",");

            Set newSet = new HashSet();
            for (String email : emails) {
                Object o = null;
                if (type == ContactNumber.class) {
                    o = new ContactNumber(email);
                } else if (type == Email.class) {
                    o = new Email(email);
                } else if (type == Address.class) {
                    o = new Address(email);
                }
                newSet.add(o);
            }
            set.clear();
            set.addAll(newSet);
        } else {
            set.clear();
        }
    }

    public Set<Address> getAddress() {
        return address;
    }

    public void setAddress(Set<Address> address) {
        this.address = address;
    }

    public Set<ContactNumber> getContactNumbers() {
        return contactNumbers;
    }

    public void setContactNumbers(Set<ContactNumber> contactNumber) {
        this.contactNumbers = contactNumber;
    }

    public List<String> getNotes() {
        return notes;
    }

    public void setNotes(List<String> notes) {
        this.notes = notes;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    public void addEmail(String email) {
        emailAddress.add(new Email(email));
    }

    public void addAddress(String address, String desc) {
        Address a = new Address();
        a.setAddress(address);
        a.setDescription(desc);
        this.getAddress().add(a);
    }

    public void addNumber(String number, String desc) {
        ContactNumber n = new ContactNumber();
        n.setNumber(number);
        n.setDescription(desc);
        this.getContactNumbers().add(n);
    }

    public String getEmailAsString() {
        StringBuilder sb = new StringBuilder();
        for (Email email : getEmailAddress()) {
            sb.append(email.getEmail());
            sb.append(", ");
        }
        return toString(sb);
    }

    public String getAddressAsString() {
        StringBuilder sb = new StringBuilder();
        for (Address address : getAddress()) {
            sb.append(address.getAddress());
            sb.append(", ");
        }
        return toString(sb);
    }

    public String getContactNumberAsString() {
        StringBuilder sb = new StringBuilder();
        for (ContactNumber contact : getContactNumbers()) {
            sb.append(contact.getNumber());
            sb.append("; ");
        }
        return toString(sb);
    }

    private String toString(StringBuilder sb) {
        if (sb.length() > 1) {
            return sb.substring(0, sb.length() - 2);
        } else {
            return "";
        }
    }
    
    public String toString() {
        return getFirstName() + getMiddleName() + getLastName() + getEmailAsString() + getAddressAsString() + getContactNumberAsString();
    }
}
