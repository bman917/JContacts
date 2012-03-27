/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bman917.jcontacts.models;

import com.jchan.jtableutils.JDataModel;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Mr Jacky
 */
public class ContactEntryDataModel extends JDataModel<ContactEntry> {

    public static final String FIRST_NAME = "First Name";
    public static final String LAST_NAME = "Last Name";
    public static final String MIDDLE_NAME = "Middle Name";
    public static final String EMAIL = "Email";
    public static final String PHONE = "Phone Number";
    public static final String ADDRESS = "Address";
    
    /** A mapping for the column name to the ContactEntry field name */
    public static final Map<String, String> columnNameContactFieldMap;
    
    static {
        columnNameContactFieldMap = new HashMap<String, String>();
        columnNameContactFieldMap.put(FIRST_NAME, "firstName");
        columnNameContactFieldMap.put(LAST_NAME, "lastName");
        columnNameContactFieldMap.put(MIDDLE_NAME, "middleName");
        columnNameContactFieldMap.put(EMAIL, "emailAsString");
    }

    public ContactEntryDataModel() {
        super(ContactEntry.class);
    }

    @Override
    public String getFeild(ContactEntry genericType, int index) {
        String columName = getColumnName(index);

        if (genericType == null) {
            return "";
        }

        if (EMAIL.equals(columName)) {
            return genericType.getEmailAsString();
        } else if (PHONE.equals(columName)) {
            return genericType.getContactNumberAsString();
        } else if (ADDRESS.equals(columName)) {
            return genericType.getAddressAsString();
        } else {
            return super.getFeild(genericType, index);
        }
    }

    @Override
    public void setField(Object value, ContactEntry genericType, int index) {
        System.out.println("Setting value to: " + value);

        String columName = getColumnName(index);
        String val = String.valueOf(value);

        if (EMAIL.equals(columName)) {
            genericType.replaceEmailAddress(val);
        } else if (PHONE.equals(columName)) {
            genericType.replaceContactNumber(val);
        } else if (ADDRESS.equals(columName)) {
            genericType.replaceAddress(val);
        } else {
            super.setField(value, genericType, index);
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
