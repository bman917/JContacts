/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bman917.jcontacts;

import com.bman917.jcontacts.models.ContactEntry;
import java.io.InputStream;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.apache.commons.io.IOUtils;
import org.apache.commons.io.LineIterator;

/**
 *
 * @author Mr Jacky
 */
public class DummyDataLoader {

    public static void main(String[] args) throws Exception {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("Contact");
        EntityManager em = emf.createEntityManager();

        InputStream is = ClassLoader.getSystemResourceAsStream("Data");
        LineIterator it = IOUtils.lineIterator(is, "UTF-8");
        try {
            System.out.println("---------------------------------");
            System.out.println("Starting Database Transaction....");
            System.out.println("---------------------------------");
            
            em.getTransaction().begin();
            while (it.hasNext()) {
                String line = it.nextLine();
                System.out.println(line);

                String[] ent = line.split(",");

                String fName = ent[0].trim();
                String mName = ent[1].trim();
                String lName = ent[2].trim();
                String emails = ent[3].trim().replaceAll(";", ",");
                String phns = ent[4].trim().replaceAll(";", ",");
                String addrs = ent[5].trim().replaceAll(";", ",");

                ContactEntry ce = ContactEntry.create(fName, mName, lName, emails, addrs, phns);
                System.out.println("Pesisting: " + ce);
                em.persist(ce);
            }
            
            System.out.println("Commiting database changes....");
            em.getTransaction().commit();
            System.out.println("Commiting database changes....Succssful");
            
            System.out.println("---------------------------------");
            System.out.println("Retrieving Saved Data....");
            
            List<ContactEntry> list = em.createQuery("SELECT c FROM ContactEntry c").getResultList();
            for (Object o : list)
            {
                System.out.println(o);
            }

        } finally {
            LineIterator.closeQuietly(it);
        }
    }
}
