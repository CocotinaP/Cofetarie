package org.example.service;

import org.example.domain.model.Vanzare;
import org.example.utils.event.VanzareChangeEvent;
import org.example.utils.observer.Observable;

public interface VanzareService extends Observable<VanzareChangeEvent> {
    void save(Vanzare vanzare);
}
