package me.arsnotfound.mycontacts.repo.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Entity(tableName = "contacts")
public class ContactEntity {
    @PrimaryKey
    public long id;

    @ColumnInfo(name = "first_name")
    @NotNull
    public String firstName;

    @ColumnInfo(name="last_name")
    @NotNull
    public String lastName;

    @ColumnInfo(name="middle_name")
    @NotNull
    public String middleName;

    @ColumnInfo(name="phone_number")
    @NotNull
    public String phoneNumber;

    @ColumnInfo(name="date_of_birth")
    public String dateOfBirth;
}
