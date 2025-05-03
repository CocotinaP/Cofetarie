package org.example.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Objects;

@jakarta.persistence.Entity
@Table(name = "Produse", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"denumire"})
})
public class Produs extends Entity<Integer>{
    @NotBlank
    private String denumire;
    @Positive
    private Float pret;
    @NotBlank
    private String unitateMasura;
    @FutureOrPresent
    private LocalDate dataExpriare;
    @NotBlank
    private String ingrediente;
    private String observatii;
    @Enumerated(EnumType.STRING)
    private DisponibilitateProdus disponibilitateProdus;

    public Produs(){

    }

    public Produs(String denumire, Float pret, String unitateMasura, LocalDate dataExpriare) {
        this.denumire = denumire;
        this.pret = pret;
        this.unitateMasura = unitateMasura;
        this.dataExpriare = dataExpriare;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public Float getPret() {
        return pret;
    }

    public void setPret(Float pret) {
        this.pret = pret;
    }

    public String getUnitateMasura() {
        return unitateMasura;
    }

    public void setUnitateMasura(String unitateMasura) {
        this.unitateMasura = unitateMasura;
    }

    public LocalDate getDataExpriare() {
        return dataExpriare;
    }

    public void setDataExpriare(LocalDate dataExpriare) {
        this.dataExpriare = dataExpriare;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public DisponibilitateProdus getDisponibiliateProdus() {
        return disponibilitateProdus;
    }

    public void setDisponibiliateProdus(DisponibilitateProdus disponibilitateProdus) {
        this.disponibilitateProdus = disponibilitateProdus;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Produs produs = (Produs) o;
        return Objects.equals(denumire, produs.denumire);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), denumire);
    }

    @Override
    public String toString() {
        return "Denumire: " + denumire +
                "| Pret: " + pret +
                "| U.M.: " + unitateMasura +
                "| Data expriarii: " + dataExpriare +
                "| Ingrediente: " + ingrediente +
                "| Observatii: " + observatii +
                "| Disponibiliate produs: " + disponibilitateProdus;
    }
}
