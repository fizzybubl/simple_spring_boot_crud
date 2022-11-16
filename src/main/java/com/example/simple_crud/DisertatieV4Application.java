package com.example.disertatie_v4;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: add validation logic for payload fields
//  add logic for NoSuchElement exceptions and other types of exceptions, to send corresponding messages
//  write integration tests to check the validation
//  write code to return an error if a company does not exist when an employee is created or updated

@SpringBootApplication
public class DisertatieV4Application {

    public static void main(String[] args) {
        SpringApplication.run(DisertatieV4Application.class, args);
    }

}
