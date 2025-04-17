package me.arsnotfound.mycontacts.data;

import java.util.List;

public interface ContactRepository {
    void insert(Contact contact);

    void update(Contact contact);

    void deleteAll();

    void delete(long id);

    void delete(Contact contact);

    Contact select(long id);

    List<Contact> selectAll();
}
