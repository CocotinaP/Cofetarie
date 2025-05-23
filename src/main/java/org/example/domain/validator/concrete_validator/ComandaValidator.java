package org.example.domain.validator.concrete_validator;

import org.example.domain.model.Comanda;
import org.example.domain.validator.Validator;
import org.example.domain.validator.exception.ValidationException;

import java.text.DecimalFormat;

public class ComandaValidator implements Validator<Comanda> {
    private static ItemComandaValidator itemComandaValidator = new ItemComandaValidator();
    @Override
    public void validate(Comanda entity) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            entity.getProduseComandate().forEach(itemComandaValidator::validate);
        }catch (ValidationException validationException){
            stringBuilder.append(validationException);
        }

        try{
            validateAvans(entity);
        }catch (ValidationException validationException){
            stringBuilder.append(validationException);
        }

        if (!stringBuilder.isEmpty()){
            throw new ValidationException(stringBuilder.toString());
        }
    }

    private void validateAvans(Comanda entity){
        DecimalFormat decimalFormat = new DecimalFormat("#.00");
        var avansNecesar = Float.parseFloat(decimalFormat.format(entity.getPret() * 0.3));

        if (avansNecesar > entity.getAvans()){
            throw new ValidationException("Avansul trebuie să fie minim " + avansNecesar + "!\n");
        }
    }
}
