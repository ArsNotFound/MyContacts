package me.arsnotfound.mycontacts;

import android.app.Application;

import me.arsnotfound.mycontacts.repo.ContactsDB;

public class MyContactsApplication extends Application {
    private volatile ContactsDB contactsDB;

    public ContactsDB getContactsDB() {
        if (contactsDB == null) {
            synchronized (this) {
                if (contactsDB == null) {
                    contactsDB = new ContactsDB(this);
                }
            }
        }
        return contactsDB;
    }
}
