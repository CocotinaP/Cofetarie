package org.example.domain.model;

import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tip_angajat", discriminatorType = DiscriminatorType.STRING)
@Table(name = "Angajati", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"cnp"}),
        @UniqueConstraint(columnNames = {"email"}),
        @UniqueConstraint(columnNames = {"nrTel"}),
        @UniqueConstraint(columnNames = {"username"}),
        @UniqueConstraint(columnNames = {"parola"})
})
public class Angajat extends org.example.domain.model.Entity<Integer> {
    @NotBlank
    private String nume;
    @NotBlank
    private String prenume;
    @Column(columnDefinition = "CHAR(13)")
    private String cnp;
    @NotBlank
    private String adresa;
    @NotBlank
    @Column(length = 20)
    private String nrTel;
    @Email
    private String email;
    @NotBlank
    private String username;
    @NotBlank
    private String parola;
    @Positive
    private Float salariu;
    @PastOrPresent
    private LocalDate dataAngajarii;
    private LocalDate dataPlecarii;

    public Angajat(){

    }

    public Angajat(String nume, String prenume, String cnp, String adresa, String nrTel) {
        this.nume = nume;
        this.prenume = prenume;
        this.cnp = cnp;
        this.adresa = adresa;
        this.nrTel = nrTel;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getParola() {
        return parola;
    }

    public void setParola(String parola) {
        this.parola = parola;
    }

    public Float getSalariu() {
        return salariu;
    }

    public void setSalariu(Float salariu) {
        this.salariu = salariu;
    }

    public LocalDate getDataAngajarii() {
        return dataAngajarii;
    }

    public void setDataAngajarii(LocalDate dataAngajarii) {
        this.dataAngajarii = dataAngajarii;
    }

    public LocalDate getDataPlecarii() {
        return dataPlecarii;
    }

    public void setDataPlecarii(LocalDate dataPlecarii) {
        this.dataPlecarii = dataPlecarii;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Angajat angajat = (Angajat) o;
        return Objects.equals(cnp, angajat.cnp);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(cnp);
    }

    @Override
    public String toString() {
        return "Nume: " + nume + ' ' + prenume +
                "| CNP: " + cnp +
                "| NrTel.: " + nrTel;
    }
}
