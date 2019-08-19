package com.cassiomolin.example.util;

import com.cassiomolin.example.model.Contact;

import java.time.OffsetDateTime;
import java.util.List;

public final class ContactDataFactory {

    public ContactDataFactory() {
        throw new AssertionError("No instances for you!");
    }

    public static Contact johnDoe() {
        Contact contact = new Contact();
        contact.setId(1);
        contact.setFirstName("John");
        contact.setLastName("Doe");
        contact.setEmails(List.of("john.doe@mail.com"));
        contact.setCreatedDateTime(OffsetDateTime.parse("2019-08-19T20:30:00Z"));
        return contact;
    }

    public static Contact janePoe() {
        Contact contact = new Contact();
        contact.setId(2);
        contact.setFirstName("Jane");
        contact.setLastName("Poe");
        contact.setEmails(List.of("jane.poe@mail.com", "janep@mail.com"));
        contact.setCreatedDateTime(OffsetDateTime.parse("2019-08-19T20:45:00Z"));
        return contact;
    }
}
