package com.example.javaclicode.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


import java.util.UUID;

@Entity
@Table(name = "client_apartments")
@Getter
@Setter
public class ClientApartment {

    @Id
    @GeneratedValue
    private UUID apartmentId;
    private String description;
    private String buildingName;
    private String streetAddress;
    private String city;
    private String postalCode;
    private boolean isAvailableForRent;
    private int rentPrice;
}
