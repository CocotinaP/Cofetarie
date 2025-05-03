package org.example.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;

import java.util.List;

@Entity
@DiscriminatorValue("COFETAR")
public class Cofetar extends Angajat{
    @ManyToMany(mappedBy = "cofetari")
    private List<Comanda> comenzi;

    public Cofetar() {

    }

    public Cofetar(String nume, String prenume, String cnp, String adresa, String nrTel) {
        super(nume, prenume, cnp, adresa, nrTel);
    }

    public List<Comanda> getComezi() {
        return comenzi;
    }

    public void setComezi(List<Comanda> comezi) {
        this.comenzi = comezi;
    }
}
