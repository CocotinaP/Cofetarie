package org.example.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ADMINISTRATOR")
public class Adminsitrator extends Angajat{
    public Adminsitrator(){

    }

    public Adminsitrator(String nume, String prenume, String cnp, String adresa, String nrTel) {
        super(nume, prenume, cnp, adresa, nrTel);
    }
}
