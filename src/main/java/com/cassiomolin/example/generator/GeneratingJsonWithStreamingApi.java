package com.cassiomolin.example.generator;

import com.cassiomolin.example.model.Contact;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.cassiomolin.example.util.ContactDataFactory.janePoe;
import static com.cassiomolin.example.util.ContactDataFactory.johnDoe;

public class GeneratingJsonWithStreamingApi {

    public static void main(String[] args) {

        GeneratingJsonWithStreamingApi example = new GeneratingJsonWithStreamingApi();
        List<Contact> contacts = List.of(johnDoe(), janePoe());

        try (OutputStream os = new FileOutputStream("output-streaming-api.json")) {
            example.generateJson(contacts, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateJson(List<Contact> contacts, OutputStream os) throws IOException {

        // Create a factory which will be used for creating a JsonGenerator instance
        JsonFactory jsonFactory = new JsonFactory();

        // Create a JsonGenerator instance
        try (JsonGenerator jsonGenerator = jsonFactory.createGenerator(os)) {

            // Configure the JsonGenerator to pretty print the output
            jsonGenerator.useDefaultPrettyPrinter();

            // Write the start array token
            jsonGenerator.writeStartArray();

            // Iterate over the contacts and write each contact as a JSON object
            for (Contact contact : contacts) {
                writeContact(jsonGenerator, contact);
            }

            // Write the end array token
            jsonGenerator.writeEndArray();
        }
    }

    private void writeContact(JsonGenerator jsonGenerator, Contact contact) throws IOException {

        // Write the start object token
        jsonGenerator.writeStartObject();

        // Write each field of the contact instance as a property/value pair
        jsonGenerator.writeNumberField("id", contact.getId());
        jsonGenerator.writeStringField("firstName", contact.getFirstName());
        jsonGenerator.writeStringField("lastName", contact.getLastName());
        jsonGenerator.writeFieldName("emails");
        writeEmails(jsonGenerator, contact.getEmails());

        // Write the end object token
        jsonGenerator.writeEndObject();
    }

    private void writeEmails(JsonGenerator jsonGenerator, List<String> emails) throws IOException {

        // Write the start array token
        jsonGenerator.writeStartArray();

        // Iterate over the emails and write each emails as a string
        for (String email : emails) {
            jsonGenerator.writeString(email);
        }

        // Write the end array token
        jsonGenerator.writeEndArray();
    }
}
