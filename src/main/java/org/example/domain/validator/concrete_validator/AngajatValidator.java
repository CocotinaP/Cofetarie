package org.example.domain.validator.concrete_validator;

import org.example.domain.model.Angajat;
import org.example.domain.validator.Validator;
import org.example.domain.validator.exception.ValidationException;

public class AngajatValidator implements Validator<Angajat> {

    @Override
    public void validate(Angajat entity) {
        StringBuilder errorMessages = new StringBuilder();

        if (entity.getNume().isEmpty()){
            errorMessages.append("Numele nu poate fi lber!\n");
        }

        if (entity.getPrenume().isEmpty()){
            errorMessages.append("Prenumele nu poate fi liber!\n");
        }

        if (entity.getCnp().length() != 13){
            errorMessages.append("CNP invalid!\n");
        }

        if (entity.getAdresa().isEmpty()){
            errorMessages.append("Adresa nu poate fi invalida!\n");
        }

        if (entity.getSalariu() < 0){
            errorMessages.append("Salariul nu poate fi negativ!\n");
        }

        String nrTel = entity.getNrTel();

        if (nrTel.isEmpty() || !nrTel.matches("^[0-9 -.]+$")){
            errorMessages.append("Numar de telefon invalid!");
        }

        if (!errorMessages.isEmpty()){
            throw new ValidationException(errorMessages.toString());
        }
    }
}
