package org.example.service;

import org.example.domain.model.Angajat;

public interface Service {
    Angajat login(String username);

    void setAngajatService(AngajatService angajatService);

    void setAdministratorService(AdministratorService administratorService);

    void setCofetarService(CofetarService cofetarService);

    void setVanzatorService(VanzatorService vanzatorService);

    void setProdusService(ProdusService produsService);

    AdministratorService getAdministratorService();

    AngajatService getAngajatService();

    CofetarService getCofetarService();

    VanzatorService getVanzatorService();

    ProdusService getProdusService();
}
