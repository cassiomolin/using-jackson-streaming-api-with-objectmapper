package com.cassiomolin.example.generator;

import com.cassiomolin.example.model.Contact;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.cassiomolin.example.util.ContactDataFactory.janePoe;
import static com.cassiomolin.example.util.ContactDataFactory.johnDoe;

public class GeneratingJsonWithStreamingApiAndObjectMapper {

    public static void main(String[] args) {

        GeneratingJsonWithStreamingApiAndObjectMapper example = new GeneratingJsonWithStreamingApiAndObjectMapper();
        List<Contact> contacts = List.of(johnDoe(), janePoe());

        try (OutputStream os = new FileOutputStream("output-streaming-api-with-objectmapper.json")) {
            example.generateJson(contacts, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateJson(List<Contact> contacts, OutputStream os) throws IOException {

        // Create and configure an ObjectMapper instance
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);

        // Create a JsonGenerator instance
        try (JsonGenerator jsonGenerator = mapper.getFactory().createGenerator(os)) {

            // Write the start array token
            jsonGenerator.writeStartArray();

            // Iterate over the contacts and write each contact as a JSON object
            for (Contact contact : contacts) {
                writeContact(mapper, jsonGenerator, contact);
            }

            // Write the end array token
            jsonGenerator.writeEndArray();
        }
    }

    private void writeContact(ObjectMapper mapper, JsonGenerator jsonGenerator, Contact contact) throws IOException {

        // Write a contact instance as JSON using ObjectMapper
        mapper.writeValue(jsonGenerator, contact);
    }
}
