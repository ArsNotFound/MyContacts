package me.arsnotfound.mycontacts.repo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import me.arsnotfound.mycontacts.data.Contact;

public class ContactsDB {
    private static final String DATABASE_NAME = "contacts.db";
    private static final int DATABASE_VERSION = 1;
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

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;

    private final SQLiteDatabase database;

    public ContactsDB(Context context) {
        OpenHelper openHelper = new OpenHelper(context);
        database = openHelper.getWritableDatabase();
    }

    public long insert(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put(COLUMN_LAST_NAME, contact.getLastName());
        cv.put(COLUMN_MIDDLE_NAME, contact.getMiddleName());
        cv.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());
        cv.put(COLUMN_DATE_OF_BIRTH, formatDate(contact.getDateOfBirth()));
        return database.insert(TABLE_NAME, null, cv);
    }

    public int update(Contact contact) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_FIRST_NAME, contact.getFirstName());
        cv.put(COLUMN_LAST_NAME, contact.getLastName());
        cv.put(COLUMN_MIDDLE_NAME, contact.getMiddleName());
        cv.put(COLUMN_PHONE_NUMBER, contact.getPhoneNumber());

        LocalDate dateOfBirth = contact.getDateOfBirth();
        cv.put(COLUMN_DATE_OF_BIRTH, dateOfBirth != null ? dateOfBirth.format(DATE_TIME_FORMATTER) : null);
        return database.update(TABLE_NAME, cv, COLUMN_ID + " = ?", new String[]{String.valueOf(contact.getID())});
    }

    public int deleteAll() {
        return database.delete(TABLE_NAME, null, null);
    }

    public int delete(long id) {
        return database.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[]{String.valueOf(id)});
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

    public static @Nullable LocalDate parseDate(@Nullable String rawDate) {
        return rawDate != null ? LocalDate.parse(rawDate, DATE_TIME_FORMATTER) : null;
    }

    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String query = "CREATE TABLE " + TABLE_NAME + " (" +
                    COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_FIRST_NAME + " TEXT NOT NULL, " +
                    COLUMN_LAST_NAME + " TEXT NOT NULL, " +
                    COLUMN_MIDDLE_NAME + " TEXT NOT NULL, " +
                    COLUMN_PHONE_NUMBER + " TEXT NOT NULL, " +
                    COLUMN_DATE_OF_BIRTH + " TEXT" +
                    ");";
            db.execSQL(query);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
            onCreate(db);
        }
    }
}
