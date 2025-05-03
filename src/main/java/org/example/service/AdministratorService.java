package org.example.service;

import org.example.domain.model.Adminsitrator;

import java.util.Optional;

public interface AdministratorService {
    Optional<Adminsitrator> findOneByUsername(String username);
}
