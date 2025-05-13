package com.bootcamp.springBoot.Person.model;

import lombok.Data;
//4
@Data
public class PersonRequest {
    private String firstname;
    private String lastname;
    private String gender;
}