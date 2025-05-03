package org.example.controller;

import org.example.domain.model.Vanzator;
import org.example.service.Service;

public class VanzatorHomeController {
    private Service service;
    private Vanzator vanzator;

    public void setService(Service service) {
        this.service = service;
    }

    public void setVanzator(Vanzator vanzator) {
        this.vanzator = vanzator;
    }
}
