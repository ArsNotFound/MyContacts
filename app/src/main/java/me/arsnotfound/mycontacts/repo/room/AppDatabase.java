package me.arsnotfound.mycontacts.repo.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import me.arsnotfound.mycontacts.repo.room.dao.ContactDao;
import me.arsnotfound.mycontacts.repo.room.entity.ContactEntity;

@Database(entities = {ContactEntity.class}, version = 3)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ContactDao contactDao();
}
