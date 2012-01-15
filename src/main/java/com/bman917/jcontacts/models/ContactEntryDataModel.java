/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bman917.jcontacts.models;

import com.jchan.jtableutils.JDataModel;

/**
 *
 * @author Mr Jacky
 */
public class ContactEntryDataModel extends JDataModel<ContactEntry> {

    static final String FIRST_NAME = "First Name";
    static final String LAST_NAME = "Last Name";
    static final String MIDDLE_NAME = "Middle Name";
    static final String EMAIL = "Email";
    static final String PHONE = "Phone Number";
    static final String ADDRESS = "Address";

    public ContactEntryDataModel() {
        super.addColumName(FIRST_NAME);
        super.addColumName(LAST_NAME);
        super.addColumName(MIDDLE_NAME);
        super.addColumName(EMAIL);
        super.addColumName(PHONE);
        super.addColumName(ADDRESS);
    }

    @Override
    public String getFeild(ContactEntry genericType, int index) {
        String columName = getColumnName(index);

        if (genericType == null) {
            return "";
        }

        if (FIRST_NAME.equals(columName)) {
            return genericType.getFirstName();
        } else if (LAST_NAME.equals(columName)) {
            return genericType.getLastName();
        } else if (MIDDLE_NAME.equals(columName)) {
            return genericType.getMiddleName();
        } else if (EMAIL.equals(columName)) {
            return genericType.getEmailAsString();
        } else if (PHONE.equals(columName)) {
            return genericType.getContactNumberAsString();
        } else if (ADDRESS.equals(columName)) {
            return genericType.getAddressAsString();
        }

        throw new IllegalArgumentException("Unknown colum index: " + index);
    }

    @Override
    public void setField(Object value, ContactEntry genericType, int index) {
        System.out.println("Setting value to: " + value);

        String columName = getColumnName(index);
        String val = String.valueOf(value);



        if (FIRST_NAME.equals(columName)) {
            genericType.setFirstName(val);
        } else if (LAST_NAME.equals(columName)) {
            genericType.setLastName(val);
        } else if (MIDDLE_NAME.equals(columName)) {
            genericType.setMiddleName(val);
        } else if (EMAIL.equals(columName)) {
            genericType.replaceEmailAddress(val);
        } else if (PHONE.equals(columName)) {
            genericType.replaceContactNumber(val);
        } else if (ADDRESS.equals(columName)) {
            genericType.replaceAddress(val);
        }
    }

    public void add(String firstName, String lastName, String middleName) {
        ContactEntry ce = new ContactEntry();

        ce.setFirstName(firstName);
        ce.setLastName(lastName);
        ce.setMiddleName(middleName);

        super.addRow(ce);
    }
    
    @Override
    public void addBlankRow() {
        add("","","");
    }
}
