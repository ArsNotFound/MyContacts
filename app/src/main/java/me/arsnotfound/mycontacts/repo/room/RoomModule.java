package me.arsnotfound.mycontacts.repo.room;

import android.content.Context;

import androidx.room.Room;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.qualifiers.ApplicationContext;
import dagger.hilt.components.SingletonComponent;
import me.arsnotfound.mycontacts.data.ContactRepository;
import me.arsnotfound.mycontacts.repo.room.dao.ContactDao;

@Module
@InstallIn(SingletonComponent.class)
public abstract class RoomModule {
    @Singleton
    @Provides
    public static AppDatabase provideAppDatabase(@ApplicationContext Context appContext) {
        return Room.databaseBuilder(
                        appContext,
                        AppDatabase.class,
                        "contacts.db"
                )
                .allowMainThreadQueries()
                .build();
    }

    @Singleton
    @Provides
    public static ContactDao provideContactDao(AppDatabase db) {
        return db.contactDao();
    }

    @Binds
    public abstract ContactRepository bindContactRepository(
            ContactRoomRepository contactRoomRepository
    );
}
