package me.arsnotfound.mycontacts.repo.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import me.arsnotfound.mycontacts.repo.room.entity.ContactEntity;

@Dao
public interface ContactDao {
    @Insert
    long insert(ContactEntity contactEntity);

    @Update
    int update(ContactEntity contactEntity);

    @Delete
    int delete(ContactEntity contactEntity);

    @Query("DELETE FROM contacts WHERE id = (:id)")
    int delete(long id);

    @Query("DELETE FROM contacts")
    int deleteAll();

    @Query("SELECT * FROM contacts WHERE id = (:id)")
    LiveData<ContactEntity> select(long id);

    @Query("SELECT * FROM contacts")
    LiveData<List<ContactEntity>> selectAll();
}
