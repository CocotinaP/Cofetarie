package org.example.service.concrete_service;

import org.example.domain.model.Adminsitrator;
import org.example.persistence.AdministratorReposiotry;
import org.example.service.AdministratorService;

import java.util.Optional;

public class ConcreteAdministratorService implements AdministratorService {
    private final AdministratorReposiotry administratorReposiotry;

    public ConcreteAdministratorService(AdministratorReposiotry administratorReposiotry){
        this.administratorReposiotry = administratorReposiotry;
    }

    @Override
    public Optional<Adminsitrator> findOneByUsername(String username) {
        return administratorReposiotry.findOneByUsername(username);
    }
}
