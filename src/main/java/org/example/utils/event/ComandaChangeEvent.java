package org.example.utils.event;

import org.example.domain.model.Comanda;

public class ComandaChangeEvent implements Event{
    private ChangeEventType eventType;
    private Comanda oldValue, newValue;

    public ComandaChangeEvent(ChangeEventType eventType, Comanda newValue) {
        this.eventType = eventType;
        this.newValue = newValue;
    }

    public ComandaChangeEvent(ChangeEventType eventType, Comanda oldValue, Comanda newValue) {
        this.eventType = eventType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public ChangeEventType getEventType() {
        return eventType;
    }

    public Comanda getOldValue() {
        return oldValue;
    }

    public Comanda getNewValue() {
        return newValue;
    }
}
