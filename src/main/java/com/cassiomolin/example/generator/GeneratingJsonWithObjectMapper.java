package com.cassiomolin.example.generator;

import com.cassiomolin.example.model.Contact;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import static com.cassiomolin.example.util.ContactDataFactory.janePoe;
import static com.cassiomolin.example.util.ContactDataFactory.johnDoe;

public class GeneratingJsonWithObjectMapper {

    public static void main(String[] args) {

        GeneratingJsonWithObjectMapper example = new GeneratingJsonWithObjectMapper();
        List<Contact> contacts = List.of(johnDoe(), janePoe());

        try (OutputStream os = new FileOutputStream("output-objectmapper.json")) {
            example.generateJson(contacts, os);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void generateJson(List<Contact> contacts, OutputStream os) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.writeValue(os, contacts);
    }
}
