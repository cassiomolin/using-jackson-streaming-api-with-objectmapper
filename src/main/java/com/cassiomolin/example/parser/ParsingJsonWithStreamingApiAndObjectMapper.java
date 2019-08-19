package com.cassiomolin.example.parser;

import com.cassiomolin.example.model.Contact;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class ParsingJsonWithStreamingApiAndObjectMapper {

    public static void main(String[] args) {

        ParsingJsonWithStreamingApiAndObjectMapper example = new ParsingJsonWithStreamingApiAndObjectMapper();

        try (InputStream is = new FileInputStream("input.json")) {
            example.parseContent(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void parseContent(InputStream is) throws IOException {

        // Create and configure an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        // Create a JsonParser instance
        try (JsonParser jsonParser = mapper.getFactory().createParser(is)) {

            // Check the first token
            if (jsonParser.nextToken() != JsonToken.START_ARRAY) {
                throw new IllegalStateException("Expected content to be an array");
            }

            // Iterate over the tokens until the end of the array
            while (jsonParser.nextToken() != JsonToken.END_ARRAY) {

                // Read contact and do something with it
                Contact contact = readContact(mapper, jsonParser);
                doSomethingWithContact(contact);
            }
        }
    }

    private Contact readContact(ObjectMapper mapper, JsonParser jsonParser) throws IOException {

        // Read a contact instance using the ObjectMapper
        return mapper.readValue(jsonParser, Contact.class);
    }

    private void doSomethingWithContact(Contact contact) {

        // Nothing much interesting here
        // Just print the contact
        System.out.println(contact);
    }
}
