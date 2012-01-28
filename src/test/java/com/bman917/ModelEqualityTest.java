package com.bman917;

import com.bman917.jcontacts.models.Address;

import junit.framework.TestCase;

public class ModelEqualityTest extends TestCase {

    public void testAddress() {
        Address a1 = new Address("2307 Waling-Waling Street, LaColina Subdv.");
        Address a2 = new Address("2307 Waling-Waling Street, LaColina Subdv.");
        Address a3 = new Address("Room 2306 Rada Reagency Condo, Rada Street, Makati");

        assertEquals(a1, a2);
        assertNotSame(a1, a3);
        assertNotSame(a2, a3);
    }
}
