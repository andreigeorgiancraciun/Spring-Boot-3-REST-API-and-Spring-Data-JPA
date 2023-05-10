package com.example.javaclicode.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ClientDTO {
    private String fullName;
    private String email;
    private String phone;

    private List<ClientApartmentDTO> apartments;
}
