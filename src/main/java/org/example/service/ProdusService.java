package org.example.service;

import org.example.domain.model.Produs;

import java.util.Optional;

public interface ProdusService {
    void save(Produs produs);

    void update(Produs produs);

    void delete(Integer id);

    Optional<Produs> findOne(Integer id);

    Iterable<Produs> findAll();
}
