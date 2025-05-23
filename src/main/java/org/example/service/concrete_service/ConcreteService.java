package org.example.service.concrete_service;

import org.example.domain.model.Angajat;
import org.example.service.*;
import org.example.service.exception.ServiceException;

public class ConcreteService implements Service {
    private AngajatService angajatService;
    private AdministratorService administratorService;
    private CofetarService cofetarService;
    private VanzatorService vanzatorService;
    private ProdusService produsService;
    private VanzareService vanzareService;
    private ComandaService comandaService;

    @Override
    public Angajat login(String username) {
        var angajat = angajatService.findOneByUsername(username);
        if (angajat.isEmpty()){
            throw new ServiceException("Username inexistent!\n");
        }
        return angajat.get();
    }

    @Override
    public void setAngajatService(AngajatService angajatService) {
        this.angajatService = angajatService;
    }

    @Override
    public void setAdministratorService(AdministratorService administratorService) {
        this.administratorService = administratorService;
    }

    @Override
    public void setCofetarService(CofetarService cofetarService) {
        this.cofetarService = cofetarService;
    }

    @Override
    public void setVanzatorService(VanzatorService vanzatorService) {
        this.vanzatorService = vanzatorService;
    }

    @Override
    public void setProdusService(ProdusService produsService) {
        this.produsService = produsService;
    }

    @Override
    public void setVanzareService(VanzareService vanzareService) {
        this.vanzareService = vanzareService;
    }

    @Override
    public void setComandaSerice(ComandaService comandaSerice) {
        this.comandaService = comandaSerice;
    }

    @Override
    public AdministratorService getAdministratorService() {
        return administratorService;
    }

    @Override
    public AngajatService getAngajatService() {
        return angajatService;
    }

    @Override
    public CofetarService getCofetarService() {
        return cofetarService;
    }

    @Override
    public VanzatorService getVanzatorService() {
        return vanzatorService;
    }

    @Override
    public ProdusService getProdusService() {
        return produsService;
    }

    @Override
    public VanzareService getVanzareService() {
        return vanzareService;
    }

    @Override
    public ComandaService getComandaService() {
        return comandaService;
    }
}
