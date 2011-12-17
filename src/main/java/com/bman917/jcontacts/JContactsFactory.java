/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bman917.jcontacts;

import java.util.ServiceLoader;

/**
 *
 * @author Mr Jacky
 */
public final class JContactsFactory {
    
    /** Prevent instantiation */
    private JContactsFactory(){
        
    }

    public static JContactService getInstance() {
        for (JContactService sv : ServiceLoader.load(JContactService.class)) {
            return sv;
        }
        return null;
    }
}
