package org.example.persistence;

import org.example.domain.model.Comanda;
import org.example.domain.model.StatusComanda;

public interface ComandaRepository extends Repository<Integer, Comanda> {
    Iterable<Comanda> filterByStatus(StatusComanda statusComanda);
}
