package org.example.service.concrete_service;

import org.example.domain.model.Angajat;
import org.example.persistence.AngajatRepository;
import org.example.service.AngajatService;

import java.util.Optional;

public class ConcreteAngajatService implements AngajatService {
    private final AngajatRepository<Angajat> angajatRepository;

    public ConcreteAngajatService(AngajatRepository<Angajat> angajatRepository) {
        this.angajatRepository = angajatRepository;
    }

    @Override
    public Optional<Angajat> findOneByUsername(String username) {
        return angajatRepository.findOneByUsername(username);
    }

    @Override
    public Iterable<Angajat> findAll() {
        return angajatRepository.findAll();
    }

    @Override
    public void save(Angajat angajat) {
        angajatRepository.save(angajat);
    }

    @Override
    public void update(Angajat angajat) {
        angajatRepository.update(angajat);
    }

    @Override
    public void delete(Integer integer) {
        angajatRepository.delete(integer);
    }
}
