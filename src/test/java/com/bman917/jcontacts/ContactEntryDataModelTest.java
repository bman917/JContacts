/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bman917.jcontacts;

import com.bman917.jcontacts.models.ContactEntry;
import com.bman917.jcontacts.models.ContactEntryDataModel;
import com.bman917.jcontacts.models.Group;
import com.jchan.jtableutils.JDataModel;
import com.jchan.jtableutils.JDataModelTestBase;
import java.util.Date;

/**
 *
 * @author Mr Jacky
 */
public class ContactEntryDataModelTest extends JDataModelTestBase<ContactEntry> {

    @Override
    public ContactEntry createEntity() {
        ContactEntry ce = new ContactEntry();
        ce.setFirstName("John");
        ce.setMiddleName("T");
        ce.setLastName("Smith");
        ce.setBirthDay(new Date());
        ce.addAddress("Metro Manila, Philippines", "Home Address");
        ce.addEmail("john.smith@yahoo.com");
        ce.addNumber("12345678", "Home Phone");
        ce.addNumber("7777777", "Office Phone");

        Group group = new Group();
        group.setName("GroupA");
        group.setDescription("Group A is a sample group.");

        ce.getGroups().add(group);

        return ce;
    }

    @Override
    public JDataModel<ContactEntry> createModel() {
        return new ContactEntryDataModel();
    }

    @Override
    public String getPersistenceUnitName() {
        return "ContactTest";
    }

    @Override
    public Object getPrimaryKey(ContactEntry t) {
        return t.getId();
    }

    public void testBasicTest() {
        this.basicTests();
    }
}
