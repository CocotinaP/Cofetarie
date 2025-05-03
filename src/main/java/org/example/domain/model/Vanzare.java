package org.example.domain.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@jakarta.persistence.Entity
@Table(name = "Vanzari")
public class Vanzare extends Entity<Integer>{
    @Column(columnDefinition = "TIMESTAMP")
    LocalDateTime data;
    @OneToMany(mappedBy = "vanzare")
    List<ItemVanzare> produseVandute;
    @ManyToOne
    @JoinColumn(name = "vanzator_id", foreignKey = @ForeignKey(name = "fk_vanzare_vanzator"))
    private Vanzator vanzator;

    public Vanzare(){

    }

    public Vanzare(LocalDateTime data, List<ItemVanzare> produseVandute) {
        this.data = data;
        this.produseVandute = produseVandute;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<ItemVanzare> getProduseVandute() {
        return produseVandute;
    }

    public void setProduseVandute(List<ItemVanzare> produseVandute) {
        this.produseVandute = produseVandute;
    }

    public Vanzator getVanzator() {
        return vanzator;
    }

    public void setVanzator(Vanzator vanzator) {
        this.vanzator = vanzator;
    }

    @Override
    public String toString() {
        return "Data=" + data +
                "| produseVandute=" + produseVandute;
    }
}
