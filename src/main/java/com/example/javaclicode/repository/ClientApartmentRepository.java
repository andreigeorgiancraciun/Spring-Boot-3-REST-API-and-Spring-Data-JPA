package com.example.javaclicode.repository;

import com.example.javaclicode.entity.ClientApartment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface ClientApartmentRepository extends JpaRepository<ClientApartment, UUID>, JpaSpecificationExecutor<ClientApartment> {
}
