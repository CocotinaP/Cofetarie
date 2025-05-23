package org.example.domain.validator.concrete_validator;

import org.example.domain.model.Produs;
import org.example.domain.validator.Validator;
import org.example.domain.validator.exception.ValidationException;

public class ProdusValidator implements Validator<Produs> {
    @Override
    public void validate(Produs entity) {
        StringBuilder errorMessages = new StringBuilder();

        if (entity.getDenumire().isEmpty()){
            errorMessages.append("Denumirea nu poate lipsi!\n");
        }

        if (entity.getPret() < 0){
            errorMessages.append("Pretul nu poate fi negativ!\n");
        }

        if (entity.getStoc() < 0){
            errorMessages.append("Stocul nu poate fi negativ!\n");
        }

        if (!errorMessages.isEmpty()){
            throw new ValidationException(errorMessages.toString());
        }
    }
}
