package com.example.javaclicode.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientApartmentDTO {
    private String description;
    private String buildingName;
    private String streetAddress;
    private String city;
    private String postalCode;
    private boolean isAvailableForRent;
    private int rentPrice;
}
