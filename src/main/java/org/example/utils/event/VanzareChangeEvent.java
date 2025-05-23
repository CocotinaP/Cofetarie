package org.example.utils.event;

import org.example.domain.model.Vanzare;

public class VanzareChangeEvent implements Event{
    private ChangeEventType changeEventType;
    private Vanzare oldValue, newValue;

    public VanzareChangeEvent(ChangeEventType changeEventType, Vanzare newValue) {
        this.changeEventType = changeEventType;
        this.newValue = newValue;
    }

    public VanzareChangeEvent(ChangeEventType changeEventType, Vanzare oldValue, Vanzare newValue) {
        this.changeEventType = changeEventType;
        this.oldValue = oldValue;
        this.newValue = newValue;
    }

    public ChangeEventType getChangeEventType() {
        return changeEventType;
    }

    public Vanzare getOldValue() {
        return oldValue;
    }

    public Vanzare getNewValue() {
        return newValue;
    }
}
