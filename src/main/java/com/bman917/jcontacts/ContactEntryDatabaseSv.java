/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bman917.jcontacts;

import com.bman917.jcontacts.models.ContactEntry;
import com.jchan.jbernate.JBernateSv;

/**
 *
 * @author Mr Jacky
 */
public class ContactEntryDatabaseSv extends JBernateSv<ContactEntry> {

    @Override
    public Class getGenericClass() {
        return ContactEntry.class;
    }
   
}
