package org.example.service.concrete_service;

import org.example.domain.model.Produs;
import org.example.persistence.ProdusRepository;
import org.example.service.ProdusService;

import java.util.Optional;

public class ConcreteProdusService implements ProdusService {
    private final ProdusRepository produsRepository;

    public ConcreteProdusService(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
    }

    @Override
    public void save(Produs produs) {
        produsRepository.save(produs);
    }

    @Override
    public void update(Produs produs) {
        produsRepository.update(produs);
    }

    @Override
    public void delete(Integer id) {
        produsRepository.delete(id);
    }

    @Override
    public Optional<Produs> findOne(Integer id) {
        return produsRepository.findOne(id);
    }

    @Override
    public Iterable<Produs> findAll() {
        return produsRepository.findAll();
    }
}
