package me.arsnotfound.mycontacts.repo.room;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Transformations;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.inject.Inject;

import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.data.ContactRepository;
import me.arsnotfound.mycontacts.repo.room.dao.ContactDao;
import me.arsnotfound.mycontacts.repo.room.mapper.ContactMapper;

public class ContactRoomRepository implements ContactRepository {

    private final ContactDao dao;

    private final Executor executor = Executors.newFixedThreadPool(4);

    public @Inject ContactRoomRepository(ContactDao dao) {
        this.dao = dao;
    }

    @Override
    public void insert(final Contact contact) {
        executor.execute(() -> dao.insert(ContactMapper.toEntity(contact)));
    }

    @Override
    public void update(final Contact contact) {
        executor.execute(() -> dao.update(ContactMapper.toEntity(contact)));
    }

    @Override
    public void deleteAll() {
        executor.execute(dao::deleteAll);
    }

    @Override
    public void delete(final long id) {
        executor.execute(() -> dao.delete(id));
    }

    @Override
    public void delete(final Contact contact) {
        executor.execute(() -> dao.delete(ContactMapper.toEntity(contact)));
    }

    @Override
    public LiveData<Contact> select(long id) {
        return Transformations.map(dao.select(id), ContactMapper::fromEntity);
    }

    @Override
    public LiveData<List<Contact>> selectAll() {
        return Transformations.map(dao.selectAll(), ContactMapper::fromEntity);
    }
}
