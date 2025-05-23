package org.example.persistence;

import org.example.domain.model.DisponibilitateProdus;
import org.example.domain.model.Produs;

public interface ProdusRepository extends Repository<Integer, Produs> {
    Iterable<Produs> filterByDisponibilitate(DisponibilitateProdus disponibilitateProdus);
}
