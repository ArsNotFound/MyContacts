package me.arsnotfound.mycontacts.data;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;
import java.util.Objects;

public class Contact {
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

    @Override
    public String toString() {
        return "Contact{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    public String getDisplayName() {
        return String.join(" ", lastName, firstName, middleName);
    }
}
