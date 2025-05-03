package org.example.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@jakarta.persistence.Entity
@Table(name = "ItemiComenzi")
public class ItemComanda extends Entity<Integer>{
    @OneToOne
    private Produs produs;
    @Positive
    private Float cantitate;
    private String observatii;
    @ManyToOne
    @JoinColumn(name = "comanda_id", foreignKey = @ForeignKey(name = "fk_itemComanda_comanda"))
    private Comanda comanda;

    public ItemComanda(){

    }

    public ItemComanda(Produs produs) {
        this.produs = produs;
    }

    public Produs getProdus() {
        return produs;
    }

    public void setProdus(Produs produs) {
        this.produs = produs;
    }

    public Float getCantitate() {
        return cantitate;
    }

    public void setCantitate(Float cantitate) {
        this.cantitate = cantitate;
    }

    public String getObservatii() {
        return observatii;
    }

    public void setObservatii(String observatii) {
        this.observatii = observatii;
    }

    public Comanda getComanda() {
        return comanda;
    }

    public void setComanda(Comanda comanda) {
        this.comanda = comanda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemComanda that = (ItemComanda) o;
        return Objects.equals(produs, that.produs) && Objects.equals(observatii, that.observatii);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), produs, observatii);
    }

    @Override
    public String toString() {
        return "Produs: " + produs +
                "| Cantitate: " + cantitate +
                "| Observatii: " + observatii;
    }
}
