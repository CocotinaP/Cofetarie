package org.example.service;

import org.example.domain.model.DisponibilitateProdus;
import org.example.domain.model.Produs;
import org.example.utils.event.VanzareChangeEvent;
import org.example.utils.observer.Observer;

import java.util.Optional;

public interface ProdusService extends Observer<VanzareChangeEvent> {
    void save(Produs produs);

    void update(Produs produs);

    void delete(Integer id);

    Optional<Produs> findOne(Integer id);

    Iterable<Produs> findAll();

    Iterable<Produs> filterByDisponibilitate(DisponibilitateProdus disponibilitateProdus);
}
