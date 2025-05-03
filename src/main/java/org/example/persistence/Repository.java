package org.example.persistence;

import org.example.domain.model.Entity;
import org.example.persistence.exception.RepositoryException;

import java.util.Optional;

public interface Repository<ID, E extends Entity<ID>> {
    /**
     * Salveaza o entitate.
     *
     * @param entity - entitatea ce trebuie salvata
     * @throws RepositoryException      - daca salvarea esueaza
     * @throws IllegalArgumentException - daca enitatea este null
     */
    void save(E entity);

    /**
     * Modifica o entitate.
     *
     * @param entity entitatea ce trebuie modificata
     */
    void update(E entity);

    /**
     * Sterge o entitate.
     *
     * @param id - id-ul entitatatii ce trebuie stearsa
     */
    void delete(ID id);

    Optional<E> findOne(ID id);

    Iterable<E> findAll();
}
