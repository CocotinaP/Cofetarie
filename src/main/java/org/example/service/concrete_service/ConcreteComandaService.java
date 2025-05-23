package org.example.service.concrete_service;

import org.example.domain.model.Comanda;
import org.example.domain.model.StatusComanda;
import org.example.persistence.ComandaRepository;
import org.example.service.ComandaService;

public class ConcreteComandaService implements ComandaService {
    private ComandaRepository comandaRepository;

    public ConcreteComandaService(ComandaRepository comandaRepository) {
        this.comandaRepository = comandaRepository;
    }

    @Override
    public void save(Comanda comanda) {
        comandaRepository.save(comanda);
    }

    @Override
    public Iterable<Comanda> filterByStatus(StatusComanda statusComanda) {
        return comandaRepository.filterByStatus(statusComanda);
    }
}
