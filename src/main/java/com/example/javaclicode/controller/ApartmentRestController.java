package com.example.javaclicode.controller;

import com.example.javaclicode.dto.ClientDTO;
import com.example.javaclicode.service.ApartmentService;
import com.example.javaclicode.entity.Client;
import com.example.javaclicode.entity.ClientApartment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class ApartmentRestController {

    private final ApartmentService apartmentService;

    public ApartmentRestController(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping(value = {"/api/clients", "/api/clients/{optionalClientId}"})
    ResponseEntity<List<Client>> getAllClients(@PathVariable(name = "optionalClientId", required = false) String optionalClientId) {

        List<Client> clients = apartmentService.findClients(optionalClientId);

        return ResponseEntity.ok().body(clients);
    }

    @PostMapping(value = {"/api/client"})
    ResponseEntity<UUID> createClient(@RequestBody ClientDTO client) {

        UUID clientUUID = apartmentService.createClient(client);

        return ResponseEntity.ok().body(clientUUID);
    }

    @GetMapping(value = "/api/apartments")
    ResponseEntity<List<ClientApartment>> getAllApartmentsClients(
            @RequestParam(name = "city", required = false) String city,
            @RequestParam(name = "minPrice", required = false) Integer minPrice,
            @RequestParam(name = "maxPrice", required = false) Integer maxPrice,
            @RequestParam(name = "isAvailableForRent", required = false) Boolean isAvailableForRent) {

        List<ClientApartment> clients = apartmentService.findApartments(city, minPrice, maxPrice, isAvailableForRent);

        return ResponseEntity.ok().body(clients);
    }
}
