package org.example.service;

import org.example.domain.model.Angajat;
import org.example.domain.model.Comanda;

public interface Service {
    Angajat login(String username);

    void setAngajatService(AngajatService angajatService);

    void setAdministratorService(AdministratorService administratorService);

    void setCofetarService(CofetarService cofetarService);

    void setVanzatorService(VanzatorService vanzatorService);

    void setProdusService(ProdusService produsService);

    void setVanzareService(VanzareService vanzareService);

    void setComandaSerice(ComandaService comandaSerice);

    AdministratorService getAdministratorService();

    AngajatService getAngajatService();

    CofetarService getCofetarService();

    VanzatorService getVanzatorService();

    ProdusService getProdusService();

    VanzareService getVanzareService();

    ComandaService getComandaService();
}
