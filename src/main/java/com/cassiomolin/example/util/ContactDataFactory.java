package com.cassiomolin.example.util;

import com.cassiomolin.example.model.Contact;

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
        return contact;
    }

    public static Contact janePoe() {
        Contact contact = new Contact();
        contact.setId(2);
        contact.setFirstName("Jane");
        contact.setLastName("Poe");
        contact.setEmails(List.of("jane.poe@mail.com", "janep@mail.com"));
        return contact;
    }
}
