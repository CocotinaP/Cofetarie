package org.example.service.concrete_service;

import org.example.domain.model.Vanzator;
import org.example.persistence.VanzatorRepository;
import org.example.service.VanzatorService;

public class ConcreteVanzatorService implements VanzatorService {
    private final VanzatorRepository vanzatorRepository;

    public ConcreteVanzatorService(VanzatorRepository vanzatorRepository) {
        this.vanzatorRepository = vanzatorRepository;
    }

    @Override
    public void save(Vanzator vanzator) {
        vanzatorRepository.save(vanzator);
    }
}
