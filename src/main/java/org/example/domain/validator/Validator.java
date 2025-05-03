package org.example.domain.validator;

import org.example.domain.model.Entity;

public interface Validator<E> {
    void validate(E entity);
}
