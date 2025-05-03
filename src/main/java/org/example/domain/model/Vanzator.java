package org.example.domain.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
@DiscriminatorValue("VANZATOR")
public class Vanzator extends Angajat{
    @OneToMany(mappedBy = "vanzator")
    private List<Vanzare> vanzari;
    @ManyToMany(mappedBy = "vanzatori")
    private List<Comanda> comenzi;

    public Vanzator(){

    }

    public Vanzator(String nume, String prenume, String cnp, String adresa, String nrTel) {
        super(nume, prenume, cnp, adresa, nrTel);
    }

    public List<Vanzare> getVanzari() {
        return vanzari;
    }

    public void setVanzari(List<Vanzare> vanzari) {
        this.vanzari = vanzari;
    }

    public List<Comanda> getComenzi() {
        return comenzi;
    }

    public void setComenzi(List<Comanda> comenzi) {
        this.comenzi = comenzi;
    }
}
