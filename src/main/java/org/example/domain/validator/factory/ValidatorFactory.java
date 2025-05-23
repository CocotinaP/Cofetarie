package org.example.domain.validator.factory;

import org.example.domain.validator.Validator;
import org.example.domain.validator.concrete_validator.*;

public class ValidatorFactory {
    private static ValidatorFactory instance;

    private ValidatorFactory(){

    }

    public Validator getValidator(ValidatorType validatorType){
        return switch (validatorType) {
            case ANGAJAT -> new AngajatValidator();
            case PRODUS -> new ProdusValidator();
            case ITEM_VANZARE -> new ItemVanzareValidator();
            case ITEM_COMANDA -> new ItemComandaValidator();
            case COMANDA -> new ComandaValidator();
            default -> null;
        };
    }

    public static ValidatorFactory getInstance(){
        if (instance == null){
            instance = new ValidatorFactory();
        }

        return instance;
    }
}
