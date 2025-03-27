package me.arsnotfound.mycontacts.data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Objects;

public class Contact implements Parcelable {
    private String firstName;
    private String lastName;
    private String middleName;
    private String phoneNumber;
    private LocalDate dateOfBirth;

    public Contact(
            @NotNull String firstName,
            @NotNull String lastName,
            @NotNull String middleName,
            @NotNull String phoneNumber,
            @Nullable LocalDate dateOfBirth
    ) {
        setFirstName(firstName);
        setLastName(lastName);
        setMiddleName(middleName);
        setPhoneNumber(phoneNumber);
        setDateOfBirth(dateOfBirth);
    }
    
    public Contact(
            @NotNull String firstName, 
            @NotNull String lastName, 
            @NotNull String middleName, 
            @NotNull String phoneNumber
    ) {
        this(firstName, lastName, middleName, phoneNumber, null);
    }

    public Contact(@NotNull String firstName, @NotNull String lastName, @NotNull String phoneNumber) {
        this(firstName, lastName, "", phoneNumber);
    }

    public Contact(@NotNull String firstName, @NotNull String phoneNumber) {
        this(firstName, "", phoneNumber);
    }

    protected Contact(Parcel in) {
        firstName = in.readString();
        lastName = in.readString();
        middleName = in.readString();
        phoneNumber = in.readString();
        dateOfBirth = (LocalDate) in.readSerializable();
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        Objects.requireNonNull(firstName);
        this.firstName = firstName;
    }

    public @NotNull String getLastName() {
        return lastName;
    }

    public void setLastName(@NotNull String lastName) {
        Objects.requireNonNull(lastName);
        this.lastName = lastName;
    }

    public @NotNull String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@NotNull String middleName) {
        Objects.requireNonNull(middleName);
        this.middleName = middleName;
    }

    public @NotNull String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(@NotNull String phoneNumber) {
        Objects.requireNonNull(phoneNumber);
        this.phoneNumber = phoneNumber;
    }

    public @Nullable LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(@Nullable LocalDate date) {
        this.dateOfBirth = date;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return Objects.equals(phoneNumber, contact.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(phoneNumber);
    }

    @NonNull
    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    public String getDisplayName() {
        return String.join(" ", lastName, firstName, middleName);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(firstName);
        dest.writeString(lastName);
        dest.writeString(middleName);
        dest.writeString(phoneNumber);
        dest.writeSerializable(dateOfBirth);
    }

    public static final Creator<Contact> CREATOR = new Creator<>() {
        @Override
        public Contact createFromParcel(Parcel in) {
            return new Contact(in);
        }

        @Override
        public Contact[] newArray(int size) {
            return new Contact[size];
        }
    };
}
