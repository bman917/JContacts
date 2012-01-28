/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bman917.jcontacts;

import com.bman917.jcontacts.models.ContactEntry;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author Mr Jacky
 */
public class DummyDataLoader {

    public static void main(String[] args) throws Exception {
        ContactEntryDatabaseSv sv = new ContactEntryDatabaseSv();
        
        InputStream is = ClassLoader.getSystemResourceAsStream("Data");
        LineIterator it = IOUtils.lineIterator(is, "UTF-8");
        try {
            while (it.hasNext()) {
                String line = it.nextLine();
                System.out.println(line);

                String[] ent = line.split(",");
                
                String fName = ent[0].trim();
                String mName = ent[1].trim();
                String lName = ent[2].trim();
                String emails = ent[3].trim().replaceAll(";",",");
                String phns = ent[4].trim().replaceAll(";",",");
                String addrs = ent[5].trim().replaceAll(";",",");
                
                ContactEntry ce = ContactEntry.create(fName,mName,lName, emails, addrs, phns);
                System.out.println(ce);
                sv.save(ce);
            }
        } finally {
            LineIterator.closeQuietly(it);
        }
    }
}
