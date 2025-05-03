package org.example.domain.validator.concrete_validator;

import org.example.domain.model.ItemVanzare;
import org.example.domain.validator.Validator;
import org.example.domain.validator.exception.ValidationException;

public class ItemVanzareValidator implements Validator<ItemVanzare> {
    @Override
    public void validate(ItemVanzare entity) {
        StringBuilder errorMessages = new StringBuilder();

        if (entity.getCantitate() < 0){
            errorMessages.append("Cantitatea nu poate fi negativa!\n");
        }

        if (!errorMessages.isEmpty()){
            throw new ValidationException(errorMessages.toString());
        }
    }
}
