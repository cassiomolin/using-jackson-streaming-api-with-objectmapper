package com.cassiomolin.example.parser;

import com.cassiomolin.example.model.Contact;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class ParsingJsonWithObjectMapper {

    public static void main(String[] args) {

        ParsingJsonWithObjectMapper example = new ParsingJsonWithObjectMapper();

        try (InputStream is = new FileInputStream("input.json")) {
            example.parseJson(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void parseJson(InputStream is) throws IOException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());

        List<Contact> contacts = mapper.readValue(is, new TypeReference<List<Contact>>() {});
        contacts.forEach(System.out::println);
    }
}
