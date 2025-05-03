package org.example.service.concrete_service;

import org.example.domain.model.Cofetar;
import org.example.persistence.CofetarRepository;
import org.example.service.CofetarService;

public class ConcreteCofetarService implements CofetarService {
    private final CofetarRepository cofetarRepository;

    public ConcreteCofetarService(CofetarRepository cofetarRepository) {
        this.cofetarRepository = cofetarRepository;
    }

    @Override
    public void save(Cofetar cofetar) {
        cofetarRepository.save(cofetar);
    }
}
