package com.cassiomolin.example.model;

import lombok.Data;

import java.util.List;

@Data
public class Contact {
    private Integer id;
    private String firstName;
    private String lastName;
    private List<String> emails;
}