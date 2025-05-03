package org.example.domain.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDateTime;
import java.util.List;

@jakarta.persistence.Entity
@Table(name = "Comenzi", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"nrTelClient"})
})
public class Comanda extends Entity<Integer>{
    @NotBlank
    private String numeClient;
    @NotBlank
    @Column(length = 20)
    private String nrTelClient;
    @PastOrPresent
    private LocalDateTime dataPreluarii;
    @PastOrPresent
    private LocalDateTime dataRidicarii;
    @Positive
    private Float pret;
    @Positive
    private Float avans;
    @OneToMany(mappedBy = "comanda")
    private List<ItemComanda> produseComandate;
    @Enumerated(EnumType.STRING)
    private StatusComanda statusComanda;
    @ManyToMany
    @JoinTable(
            name = "Comanda_Cofetari",
            joinColumns = @JoinColumn(name = "comanda_id"),
            inverseJoinColumns = @JoinColumn(name = "cofetar_id"))
    private List<Cofetar> cofetari;
    @ManyToMany
    @JoinTable(
            name = "Comanda_Vanzator",
            joinColumns = @JoinColumn(name = "comanda_id"),
            inverseJoinColumns = @JoinColumn(name = "vanzator_id"))
    private List<Vanzator> vanzatori;

    public Comanda(){

    }

    public Comanda(String numeClient, String nrTelClient, LocalDateTime dataPreluarii, Float pret, List<ItemComanda> produseComandate) {
        this.numeClient = numeClient;
        this.nrTelClient = nrTelClient;
        this.dataPreluarii = dataPreluarii;
        this.pret = pret;
        this.produseComandate = produseComandate;
    }

    public String getNumeClient() {
        return numeClient;
    }

    public void setNumeClient(String numeClient) {
        this.numeClient = numeClient;
    }

    public String getNrTelClient() {
        return nrTelClient;
    }

    public void setNrTelClient(String nrTelClient) {
        this.nrTelClient = nrTelClient;
    }

    public LocalDateTime getDataPreluarii() {
        return dataPreluarii;
    }

    public void setDataPreluarii(LocalDateTime dataPreluarii) {
        this.dataPreluarii = dataPreluarii;
    }

    public LocalDateTime getDataRidicarii() {
        return dataRidicarii;
    }

    public void setDataRidicarii(LocalDateTime dataRidicarii) {
        this.dataRidicarii = dataRidicarii;
    }

    public Float getPret() {
        return pret;
    }

    public void setPret(Float pret) {
        this.pret = pret;
    }

    public Float getAvans() {
        return avans;
    }

    public void setAvans(Float avans) {
        this.avans = avans;
    }

    public List<ItemComanda> getProduseComandate() {
        return produseComandate;
    }

    public void setProduseComandate(List<ItemComanda> produseComandate) {
        this.produseComandate = produseComandate;
    }

    public List<Cofetar> getCofetari() {
        return cofetari;
    }

    public void setCofetari(List<Cofetar> cofetar) {
        this.cofetari = cofetar;
    }

    public StatusComanda getStatusComanda() {
        return statusComanda;
    }

    public void setStatusComanda(StatusComanda statusComanda) {
        this.statusComanda = statusComanda;
    }

    public List<Vanzator> getVanzatori() {
        return vanzatori;
    }

    public void setVanzatori(List<Vanzator> vanzator) {
        this.vanzatori = vanzator;
    }

    @Override
    public String toString() {
        return "Nume client: " + numeClient +
                "| NrTel. client: " + nrTelClient +
                "| Data preluarii: " + dataPreluarii +
                "| Data ridicarii: " + dataRidicarii +
                "| Pret: " + pret +
                "| Avans: " + avans +
                "| Produse comandate: " +
                "|Status: " + statusComanda.toString();
    }
}
