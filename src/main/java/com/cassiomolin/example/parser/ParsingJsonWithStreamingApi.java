package com.cassiomolin.example.parser;

import com.cassiomolin.example.model.Contact;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParsingJsonWithStreamingApi {

    public static void main(String[] args) {

        ParsingJsonWithStreamingApi example = new ParsingJsonWithStreamingApi();

        try (InputStream is = new FileInputStream("input.json")) {
            example.parseContent(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseContent(InputStream is) throws IOException {

        // Create a factory for creating a JsonParser instance
        JsonFactory jsonFactory = new JsonFactory();

        // Create a JsonParser instance
        try (JsonParser jsonParser = jsonFactory.createParser(is)) {

            // Check the first token
            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected content to be an array");
            }

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                // Read a contact and do something with it
                Contact contact = readContact(jsonParser);
                doSomethingWithContact(contact);
            }
        }
    }

    private Contact readContact(JsonParser jsonParser) throws IOException {

        // Check the first token
        if (jsonParser.currentToken() != JsonToken.START_OBJECT) {
            throw new IllegalStateException("Expected content to be an object");
        }

        Contact contact = new Contact();

        // Iterate over the properties of the object
        while (jsonParser.nextToken() != JsonToken.END_OBJECT) {

            // Get the current property name
            String property = jsonParser.getCurrentName();

            // Move to the corresponding value
            jsonParser.nextToken();

            // Evaluate each property name and extract the value
            switch (property) {
                case "id":
                    contact.setId(jsonParser.getIntValue());
                    break;
                case "firstName":
                    contact.setFirstName(jsonParser.getText());
                    break;
                case "lastName":
                    contact.setLastName(jsonParser.getText());
                    break;
                case "emails":
                    List<String> emails = readEmails(jsonParser);
                    contact.setEmails(emails);
                    break;
                // Unknown properties are ignored
            }
        }

        return contact;
    }

    private List<String> readEmails(JsonParser jsonParser) throws IOException {

        // Check the first token
        if (jsonParser.currentToken() != JsonToken.START_ARRAY) {
            throw new IllegalStateException("Expected content to be an object");
        }

        List<String> emails = new ArrayList<>();

        // Iterate over the tokens until the end of the array
        while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

            // Add each element of the array to the list of emails
            emails.add(jsonParser.getText());
        }

        return emails;
    }

    private void doSomethingWithContact(Contact contact) {
        System.out.println(contact);
    }
}
