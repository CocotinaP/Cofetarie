package org.example.service;

import org.example.domain.model.Comanda;
import org.example.domain.model.StatusComanda;

public interface ComandaService{
    void save(Comanda comanda);

    public Iterable<Comanda> filterByStatus(StatusComanda statusComanda);
}
