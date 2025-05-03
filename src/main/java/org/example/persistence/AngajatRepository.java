package org.example.persistence;

import org.example.domain.model.Angajat;

import java.util.Optional;

public interface AngajatRepository<A extends Angajat> extends Repository<Integer, A> {
    Optional<A> findOneByUsername(String username);
}
