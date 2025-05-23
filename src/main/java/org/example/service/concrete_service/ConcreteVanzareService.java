package org.example.service.concrete_service;

import org.example.domain.model.Vanzare;
import org.example.persistence.VanzareRepository;
import org.example.service.VanzareService;
import org.example.utils.event.ChangeEventType;
import org.example.utils.event.VanzareChangeEvent;
import org.example.utils.observer.Observable;
import org.example.utils.observer.Observer;

import java.util.ArrayList;
import java.util.List;

public class ConcreteVanzareService implements VanzareService {
    private final VanzareRepository vanzareRepository;
    private final List<Observer> observers = new ArrayList<>();

    public ConcreteVanzareService(VanzareRepository vanzareRepository) {
        this.vanzareRepository = vanzareRepository;
    }

    @Override
    public void save(Vanzare vanzare) {
        notifyObserver(new VanzareChangeEvent(ChangeEventType.ADD, vanzare));
        vanzareRepository.save(vanzare);
    }

    @Override
    public void addObserver(Observer<VanzareChangeEvent> observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer<VanzareChangeEvent> observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(VanzareChangeEvent t) {
        observers.forEach(observer -> observer.update(t));
    }
}
