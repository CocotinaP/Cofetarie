package org.example.service.concrete_service;

import org.example.domain.model.DisponibilitateProdus;
import org.example.domain.model.Produs;
import org.example.persistence.ProdusRepository;
import org.example.persistence.exception.RepositoryException;
import org.example.service.ProdusService;
import org.example.utils.event.ChangeEventType;
import org.example.utils.event.VanzareChangeEvent;
import org.example.utils.observer.Observer;

import java.util.Optional;

public class ConcreteProdusService implements ProdusService {
    private final ProdusRepository produsRepository;

    public ConcreteProdusService(ProdusRepository produsRepository) {
        this.produsRepository = produsRepository;
    }

    @Override
    public void save(Produs produs) {
        produsRepository.save(produs);
    }

    @Override
    public void update(Produs produs) {
        produsRepository.update(produs);
    }

    @Override
    public void delete(Integer id) {
        produsRepository.delete(id);
    }

    @Override
    public Optional<Produs> findOne(Integer id) {
        return produsRepository.findOne(id);
    }

    @Override
    public Iterable<Produs> findAll() {
        return produsRepository.findAll();
    }

    @Override
    public Iterable<Produs> filterByDisponibilitate(DisponibilitateProdus disponibilitateProdus) {
        return produsRepository.filterByDisponibilitate(disponibilitateProdus);
    }

    @Override
    public void update(VanzareChangeEvent vanzareChangeEvent) {
        if (vanzareChangeEvent.getChangeEventType().equals(ChangeEventType.ADD)){
            vanzareChangeEvent.getNewValue()
                    .getProduseVandute()
                    .forEach(itemVanzare -> {
                        var newProdus = itemVanzare.getProdus();

                        var oldProdus = findOne(newProdus.getId());

                        if (oldProdus.isPresent()){
                            if (itemVanzare.getCantitate() > newProdus.getStoc()){
                                throw new RepositoryException("Stoc insuficient (doar " + newProdus.getStoc() + ")!\n");
                            }
                            var stoc = oldProdus.get().getStoc() - itemVanzare.getCantitate();

                            oldProdus.get().setStoc((int) stoc);

                            update(oldProdus.get());
                        }
                    });
        }
    }
}
