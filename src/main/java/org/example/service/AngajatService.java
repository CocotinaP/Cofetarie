package org.example.service;

import org.example.domain.model.Angajat;

import java.util.Optional;

public interface AngajatService {
    Optional<Angajat> findOneByUsername(String username);

    Iterable<Angajat> findAll();

    void save(Angajat angajat);

    void update(Angajat angajat);

    void delete(Integer integer);
}
