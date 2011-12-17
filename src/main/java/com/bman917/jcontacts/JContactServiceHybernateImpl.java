package com.bman917.jcontacts;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ExceptionUtils;
import org.hibernate.exception.GenericJDBCException;

import com.bman917.jcontacts.models.Address;
import com.bman917.jcontacts.models.ContactEntry;
import com.bman917.jcontacts.models.ContactNumber;
import com.bman917.jcontacts.models.Group;
import com.bman917.jpersonic.HibernateUtil;
import com.bman917.jpersonic.Jpersonic;

public class JContactServiceHybernateImpl implements JContactService {

    private static Logger LOG = Logger.getLogger(JContactServiceHybernateImpl.class);
    String dataDir = "target/data";
    String dbName = "JContactsDB";

    public JContactServiceHybernateImpl() {
        try {

            dataDir = System.getProperty("com.bman917.jcontactsJContactServiceHybernateImpl.dataDir", "target/data");
            dbName = System.getProperty("com.bman917.jcontactsJContactServiceHybernateImpl.dbName", "JContactsDB");
            Jpersonic.startHypersonicDB(dataDir, dbName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public long saveContact(ContactEntry contact) {
        Session session = HibernateUtil.beginTransaction();

        long id = (Long) session.save(contact);
        session.disconnect();
        session.getTransaction().commit();
        return id;
    }

    @Override
    public void updateContact(ContactEntry contact) {
        Session session = HibernateUtil.beginTransaction();

        session.update(contact);
        session.getTransaction().commit();
    }

    @Override
    public void deleteContact(ContactEntry contact) {
        Session session = HibernateUtil.beginTransaction();
        session.delete(contact);
        session.getTransaction().commit();
    }

    @Override
    public void deleteContact(List<ContactEntry> contacts) {
        Session session = HibernateUtil.beginTransaction();
        for (ContactEntry contact : contacts) {
            session.delete(contact);
        }
        session.getTransaction().commit();
    }

    @Override
    public List<ContactEntry> findContact(String firstName, String lastName) {

        Session session = HibernateUtil.beginTransaction();

        Criteria c1 = session.createCriteria(ContactEntry.class);
        c1.add(Restrictions.eq("firstName", firstName));
        c1.add(Restrictions.eq("lastName", lastName));
        List<ContactEntry> list = c1.list();
        session.disconnect();
        return list;
    }

    @Override
    public List<ContactEntry> findContact(String firstName, String lastName,
            String middleName) {
        return null;

    }

    @Override
    public List<ContactEntry> findContactByEmail(String emailAddress) {
        return null;

    }

    @Override
    public List<ContactEntry> findContact(Group group) {
        return null;
    }

    @Override
    public void addContactNumber(String number, String description,
            long contactID) {

        Session session = HibernateUtil.beginTransaction();

        ContactEntry entry = getContact(session, contactID);

        // Create the ContactNumber
        ContactNumber cnumber = new ContactNumber();
        cnumber.setNumber(number);
        cnumber.setDescription(description);

        // Associate and update the datbase
        entry.getContactNumbers().add(cnumber);
        // session.update(entry);

        session.getTransaction().commit();
    }

    @Override
    public void addAddress(String address, String description,
            long contactID) {

        Session session = HibernateUtil.beginTransaction();

        ContactEntry entry = getContact(session, contactID);

        // Create the Address
        Address caddress = new Address(address);
        caddress.setDescription(description);

        // Associate and update the database
        entry.getAddress().add(caddress);
        // session.update(entry);

        session.getTransaction().commit();
    }

    @Override
    public Group createGroup(String name, String description) {
        Session session = HibernateUtil.beginTransaction();

        try {
            Group group = new Group();
            group.setName(name);
            group.setDescription(description);
            session.save(group);

            session.getTransaction().commit();
            return group;
        } catch (GenericJDBCException e) {
            System.err.println("SQL Exception is: "
                    + ExceptionUtils.getRootCause(e));
            throw e;
        }
    }

    private ContactEntry getContact(Session session, long id) {
        return (ContactEntry) session.load(ContactEntry.class, id);
    }

    @Override
    public Group assignGroup(String name, long id) {
        Session session = HibernateUtil.beginTransaction();

        ContactEntry entry = getContact(session, id);

        Group group = (Group) session.get(Group.class, name);
        LOG.debug("Found Group " + name + ": " + group);
        if (group == null) {
            group = new Group(name);
            session.save(group);
        }

        entry.getGroups().add(group);
        session.update(entry);
        session.getTransaction().commit();

        return group;
    }

    @Override
    public void updateContactEntry(ContactEntry entry) {
        Session session = HibernateUtil.beginTransaction();

        session.update(entry);

        session.getTransaction().commit();
    }

    @Override
    public void deleteAllGroups() {
        Session session = HibernateUtil.beginTransaction();

        LOG.debug("Delete all groups.");

        removeAllGroupAssociations(session);
        session.flush();

        int delete = session.createQuery("delete from Group").executeUpdate();
        LOG.debug("Deleted Groups: " + delete);
        session.getTransaction().commit();
    }

    @Override
    public void deleteAllContacts() {
        Session session = HibernateUtil.beginTransaction();

        /*
         * Unlink all groups
         */
        removeAllGroupAssociations(session);
        session.createQuery("delete from ContactNumber").executeUpdate();
        session.createQuery("delete from Address").executeUpdate();
        session.createQuery("delete from Email").executeUpdate();
        session.createSQLQuery("delete from ContactGroups").executeUpdate();
        int deleted = session.createQuery("delete from ContactEntry").executeUpdate();

        session.getTransaction().commit();

        LOG.debug("Deleted ContactEntries: " + deleted);
    }

    private void removeAllGroupAssociations(Session session) {
        LOG.debug("Deleting all group associations...");
        session.createSQLQuery("delete from ContactGroups").executeUpdate();
        LOG.debug("All Group associations has been removed.");
        session.flush();
    }

    @Override
    public void deleteGroup(String name) {
        Session session = HibernateUtil.beginTransaction();
        Group g = (Group) session.load(Group.class, name);
        session.delete(g);
    }

    @Override
    public List<ContactEntry> getAllContacts() {
        Session session = HibernateUtil.beginTransaction();

        Criteria c1 = session.createCriteria(ContactEntry.class);
        List<ContactEntry> list = c1.list();

        return list;
    }
}
