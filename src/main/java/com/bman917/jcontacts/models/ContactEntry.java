package com.bman917.jcontacts.models;

import com.jchan.jtableutils.JDataColumn;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;

@Entity
public class ContactEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    
    @JDataColumn(name = "First Name", index = 0)
    String firstName;
    
    @JDataColumn(name = "Middle Name", index = 1)
    String middleName;
    
    @JDataColumn(name = "Last Name", index = 2)
    String lastName;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JDataColumn(name = "Email", index = 3)
    Set<Email> emailAddress = new HashSet<Email>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JDataColumn(name = "Phone Number", index = 4)
    Set<ContactNumber> contactNumbers = new HashSet<ContactNumber>();
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @JDataColumn(hidden=true)
    Date birthDay;
    
    @OneToMany(cascade = CascadeType.ALL)
    @JDataColumn(name = "Address")
    Set<Address> address = new HashSet<Address>();
//    @OneToMany
//    List<String> notes = new ArrayList<String>();
    
    @OneToMany(cascade = CascadeType.ALL)
    @JDataColumn(hidden=true)
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
//
//    public List<String> getNotes() {
//        return notes;
//    }
//
//    public void setNotes(List<String> notes) {
//        this.notes = notes;
//    }

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

    public static ContactEntry create(String firstName, String lastName, String middleName,
            String commaDelimitedEmail, String commaDelimitedAddress,
            String commaDelimitedPhoneNumber) {
        ContactEntry ce = new ContactEntry();

        ce.setFirstName(firstName);
        ce.setLastName(lastName);
        ce.setMiddleName(middleName);

        ce.replaceEmailAddress(commaDelimitedEmail);
        ce.replaceAddress(commaDelimitedAddress);
        ce.replaceContactNumber(commaDelimitedPhoneNumber);

        return ce;
    }
}
