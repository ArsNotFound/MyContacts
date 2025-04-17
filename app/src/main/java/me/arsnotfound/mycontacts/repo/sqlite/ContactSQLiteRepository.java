package me.arsnotfound.mycontacts.repo.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;

import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.data.ContactRepository;

public class ContactSQLiteRepository implements ContactRepository {
    private static final String TAG = "ContactsDB";
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 3;
    private static final String TABLE_NAME = "contacts";

    private static final String COLUMN_ID = "id";
    private static final String COLUMN_FIRST_NAME = "first_name";
    private static final String COLUMN_LAST_NAME = "last_name";
    private static final String COLUMN_MIDDLE_NAME = "middle_name";
    private static final String COLUMN_PHONE_NUMBER = "phone_number";
    private static final String COLUMN_DATE_OF_BIRTH = "date_of_birth";

    private static final int NUM_COLUMN_ID = 0;
    private static final int NUM_COLUMN_FIRST_NAME = 1;
    private static final int NUM_COLUMN_LAST_NAME = 2;
    private static final int NUM_COLUMN_MIDDLE_NAME = 3;
    private static final int NUM_COLUMN_PHONE_NUMBER = 4;
    private static final int NUM_COLUMN_DATE_OF_BIRTH = 5;

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    private final SQLiteDatabase database;

    public ContactSQLiteRepository(Context context) {
        OpenHelper openHelper = new OpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public void insert(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put(COLUMN_LAST_NAME, contact.getLastName());
        cv.put(COLUMN_MIDDLE_NAME, contact.getMiddleName());
        cv.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        cv.put(COLUMN_DATE_OF_BIRTH, formatDate(contact.getDateOfBirth()));
        long id = database.insert(TABLE_NAME, null, cv);
        contact.setID(id);
    }

    public void update(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put(COLUMN_LAST_NAME, contact.getLastName());
        cv.put(COLUMN_MIDDLE_NAME, contact.getMiddleName());
        cv.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        cv.put(COLUMN_DATE_OF_BIRTH, formatDate(contact.getDateOfBirth()));
        database.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(contact.getID())});
    }

    public void deleteAll() {
        database.delete(TABLE_NAME, null, null);
    }

    public void delete(long id) {
        database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
    }

    public void delete(Contact contact) {
        this.delete(contact.getID());
    }

    public Contact select(long id) {
        try (Cursor cursor = database.query(
                TABLE_NAME,
                null,
                COLUMN_ID + " = ?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        )) {
            cursor.moveToFirst();

            return new Contact(
                    id,
                    cursor.getString(NUM_COLUMN_FIRST_NAME),
                    cursor.getString(NUM_COLUMN_LAST_NAME),
                    cursor.getString(NUM_COLUMN_MIDDLE_NAME),
                    cursor.getString(NUM_COLUMN_PHONE_NUMBER),
                    parseDate(cursor.getString(NUM_COLUMN_DATE_OF_BIRTH))
            );
        }
    }

    public ArrayList<Contact> selectAll() {
        try (Cursor cursor = database.query(
                TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        )) {
            ArrayList<Contact> arr = new ArrayList<>();
            cursor.moveToFirst();
            if (!cursor.isAfterLast()) {
                do {
                    arr.add(new Contact(
                            cursor.getLong(NUM_COLUMN_ID),
                            cursor.getString(NUM_COLUMN_FIRST_NAME),
                            cursor.getString(NUM_COLUMN_LAST_NAME),
                            cursor.getString(NUM_COLUMN_MIDDLE_NAME),
                            cursor.getString(NUM_COLUMN_PHONE_NUMBER),
                            parseDate(cursor.getString(NUM_COLUMN_DATE_OF_BIRTH))
                    ));
                } while (cursor.moveToNext());
            }

            return arr;
        }
    }

    private static @Nullable String formatDate(@Nullable LocalDate date) {
        return date != null ? date.format(DATE_TIME_FORMATTER) : null;
    }

    private static @Nullable LocalDate parseDate(@Nullable String rawDate) {
        return rawDate != null ? LocalDate.parse(rawDate, DATE_TIME_FORMATTER) : null;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                    COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                    COLUMN_MIDDLE_NAME + " TEXT NOT NULL, " +
                    COLUMN_PHONE_NUMBER + " TEXT NOT NULL, " +
                    COLUMN_DATE_OF_BIRTH + " TEXT" +
                    ");";
            db.execSQL(query);

            for (int i = 0; i < 100; i++) {
                String insQuery = "INSERT INTO " + TABLE_NAME + " (" +
                        COLUMN_FIRST_NAME + ", " +
                        COLUMN_LAST_NAME + ", " +
                        COLUMN_MIDDLE_NAME + ", " +
                        COLUMN_PHONE_NUMBER + ", " +
                        COLUMN_DATE_OF_BIRTH +
                        ") VALUES (" +
                        "'Иван " + i + "', " +
                        "'Сухарев', " +
                        "'Александрович', " +
                        "'+7(999)99999" + String.format(Locale.getDefault(), "%02d", i) + "', " +
                        "'" + LocalDate.of(1999, 12, i % 31 + 1).format(DATE_TIME_FORMATTER) + "'" +
                        ");";
                Log.i(TAG, insQuery);
                db.execSQL(insQuery);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG, "Upgrade from " + oldVersion + " to " + newVersion);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
