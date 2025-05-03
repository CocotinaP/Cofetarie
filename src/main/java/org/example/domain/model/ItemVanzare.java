package org.example.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;

import java.util.Objects;

@jakarta.persistence.Entity
@Table(name = "ItemiVanzare")
public class ItemVanzare extends Entity<Integer>{
    @OneToOne
    private Produs produs;
    @Positive
    private Float cantitate;
    @ManyToOne
    @JoinColumn(name = "vanzare_id", foreignKey = @ForeignKey(name = "fk_itemVanzare_vanzare"))
    private Vanzare vanzare;

    public ItemVanzare(){

    }

    public ItemVanzare(Produs produs, Float cantitate) {
        this.produs = produs;
        this.cantitate = cantitate;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ItemVanzare that = (ItemVanzare) o;
        return Objects.equals(produs, that.produs);
    }

    public Vanzare getVanzare() {
        return vanzare;
    }

    public void setVanzare(Vanzare vanzare) {
        this.vanzare = vanzare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), produs);
    }

    @Override
    public String toString() {
        return "Produs: " + produs + "\n" +
                "| Cantitate=" + cantitate;
    }
}
