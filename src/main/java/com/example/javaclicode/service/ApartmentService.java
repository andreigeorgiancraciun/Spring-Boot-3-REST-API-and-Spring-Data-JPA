package com.example.javaclicode.service;

import com.example.javaclicode.dto.ClientDTO;
import com.example.javaclicode.entity.Client;
import com.example.javaclicode.entity.ClientApartment;
import com.example.javaclicode.repository.ClientApartmentRepository;
import com.example.javaclicode.repository.ClientRepository;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static com.example.javaclicode.repository.ClientApartmentSpecification.*;

@Service
public class ApartmentService {

    private final ClientRepository clientRepository;
    private final ClientApartmentRepository clientApartmentRepository;

    public ApartmentService(ClientRepository clientRepository, ClientApartmentRepository apartmentRepository) {
        this.clientRepository = clientRepository;
        this.clientApartmentRepository = apartmentRepository;
    }

    public List<Client> findClients(String optionalClientId) {
        if (optionalClientId != null) {
            var clientIds = List.of(
                    UUID.fromString(optionalClientId)
            );
            return clientRepository.findAllById(clientIds);
        } else {
            return clientRepository.findAll();
        }
    }

    public List<ClientApartment> findApartments(String city, Integer minPrice, Integer maxPrice, Boolean isAvailableForRent) {
        Specification<ClientApartment> specification = Specification.where(null);

        if (city != null) {
            specification = specification.and(cityContainsIgnoreCase(city));
        }

        if (minPrice != null && maxPrice != null) {
            specification = specification.and(rentPriceBetween(minPrice, maxPrice));
        }

        if (isAvailableForRent != null) {
            specification = specification.and(isAvailableForRent(isAvailableForRent));
        }

        return clientApartmentRepository.findAll(specification);
    }

    public UUID createClient(ClientDTO clientRequest) {
        var clientEntity = new Client();

        clientEntity.setEmail(clientRequest.getEmail());
        clientEntity.setPhone(clientRequest.getPhone());
        clientEntity.setFullName(clientRequest.getFullName());

        if (clientRequest.getApartments() != null) {
            var clientApartmentEntities = new ArrayList<ClientApartment>();

            for (var apartmentRequest : clientRequest.getApartments()) {
                var apartmentEntity = new ClientApartment();

                apartmentEntity.setAvailableForRent(apartmentRequest.isAvailableForRent());
                apartmentEntity.setBuildingName(apartmentRequest.getBuildingName());
                apartmentEntity.setCity(apartmentRequest.getCity());
                apartmentEntity.setDescription(apartmentRequest.getDescription());
                apartmentEntity.setPostalCode(apartmentRequest.getPostalCode());
                apartmentEntity.setRentPrice(apartmentRequest.getRentPrice());
                apartmentEntity.setStreetAddress(apartmentRequest.getStreetAddress());

                clientApartmentEntities.add(apartmentEntity);
            }

            clientEntity.setApartments(clientApartmentEntities);
        }

        return clientRepository.save(clientEntity).getClientId();
    }
}
