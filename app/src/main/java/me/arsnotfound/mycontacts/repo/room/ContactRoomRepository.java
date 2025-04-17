package me.arsnotfound.mycontacts.repo.room;

import java.util.List;

import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.data.ContactRepository;
import me.arsnotfound.mycontacts.repo.room.dao.ContactDao;
import me.arsnotfound.mycontacts.repo.room.mapper.ContactMapper;

public class ContactRoomRepository implements ContactRepository {

    private final ContactDao dao;

    public ContactRoomRepository(ContactDao dao) {
        this.dao = dao;
    }

    @Override
    public void insert(Contact contact) {
        dao.insert(ContactMapper.toEntity(contact));
    }

    @Override
    public void update(Contact contact) {
        dao.update(ContactMapper.toEntity(contact));
    }

    @Override
    public void deleteAll() {
        dao.deleteAll();
    }

    @Override
    public void delete(long id) {
        dao.delete(id);
    }

    @Override
    public void delete(Contact contact) {
        dao.delete(ContactMapper.toEntity(contact));
    }

    @Override
    public Contact select(long id) {
        return ContactMapper.fromEntity(dao.select(id));
    }

    @Override
    public List<Contact> selectAll() {
        return ContactMapper.fromEntity(dao.selectAll());
    }
}
