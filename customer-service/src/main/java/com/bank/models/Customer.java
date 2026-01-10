package com.bank.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Customer {
    private Long id;
    private String nationalId;
    private String nationalIdType;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private int age;
    private LocalDateTime createdAt;
    private LocalDateTime lastUpdateDate;
    public Address address;
}
