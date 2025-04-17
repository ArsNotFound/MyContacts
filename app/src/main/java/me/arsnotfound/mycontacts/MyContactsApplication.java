package me.arsnotfound.mycontacts;

import android.app.Application;

import androidx.room.Room;

import me.arsnotfound.mycontacts.data.ContactRepository;
import me.arsnotfound.mycontacts.repo.room.AppDatabase;
import me.arsnotfound.mycontacts.repo.room.ContactRoomRepository;
import me.arsnotfound.mycontacts.repo.sqlite.ContactSQLiteRepository;

public class MyContactsApplication extends Application {

    private volatile ContactRepository contactRepository;

    public ContactRepository getContactRepository() {
        if (contactRepository == null) {
            synchronized (this) {
                if (contactRepository == null) {
                    AppDatabase appDatabase = Room.databaseBuilder(
                            getApplicationContext(),
                            AppDatabase.class,
                            "contacts.db"
                    )
                            .allowMainThreadQueries()
                            .build();
                    contactRepository = new ContactRoomRepository(appDatabase.contactDao());
//                    contactRepository = new ContactSQLiteRepository(getApplicationContext());
                }
            }
        }
        return contactRepository;
    }
}
