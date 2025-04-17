package me.arsnotfound.mycontacts.repo.room.mapper;

import androidx.annotation.Nullable;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import me.arsnotfound.mycontacts.data.Contact;
import me.arsnotfound.mycontacts.repo.room.entity.ContactEntity;

public final class ContactMapper {
    private ContactMapper() {
    }

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE;

    public static ContactEntity toEntity(Contact contact) {
        ContactEntity entity = new ContactEntity();
        entity.id = contact.getID();
        entity.firstName = contact.getFirstName();
        entity.lastName = contact.getLastName();
        entity.middleName = contact.getMiddleName();
        entity.phoneNumber = contact.getPhoneNumber();
        entity.dateOfBirth = formatDate(contact.getDateOfBirth());
        return entity;
    }

    public static Contact fromEntity(ContactEntity entity) {
        return new Contact(
                entity.id,
                entity.firstName,
                entity.lastName,
                entity.middleName,
                entity.phoneNumber,
                parseDate(entity.dateOfBirth)
        );
    }

    public static List<Contact> fromEntity(List<ContactEntity> entityList) {
        return entityList.stream().map(ContactMapper::fromEntity).collect(Collectors.toList());
    }

    private static @Nullable String formatDate(@Nullable LocalDate date) {
        return date != null ? date.format(DATE_TIME_FORMATTER) : null;
    }

    private static @Nullable LocalDate parseDate(@Nullable String rawDate) {
        return rawDate != null ? LocalDate.parse(rawDate, DATE_TIME_FORMATTER) : null;
    }
}
