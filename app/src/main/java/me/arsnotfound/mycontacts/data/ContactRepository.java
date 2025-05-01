package me.arsnotfound.mycontacts.data;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface ContactRepository {
    void insert(Contact contact);

    void update(Contact contact);

    void deleteAll();

    void delete(long id);

    void delete(Contact contact);

    LiveData<Contact> select(long id);

    LiveData<List<Contact>> selectAll();
}
