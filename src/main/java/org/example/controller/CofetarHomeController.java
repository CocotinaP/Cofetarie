package org.example.controller;

import org.example.domain.model.Cofetar;
import org.example.service.Service;

public class CofetarHomeController {
    private Service service;
    private Cofetar cofetar;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public Cofetar getCofetar() {
        return cofetar;
    }

    public void setCofetar(Cofetar cofetar) {
        this.cofetar = cofetar;
    }
}
