package org.example;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.controller.LoginController;
import org.example.domain.model.*;
import org.example.domain.validator.factory.ValidatorFactory;
import org.example.domain.validator.factory.ValidatorType;
import org.example.persistence.*;
import org.example.persistence.database.hibernate.*;
import org.example.service.*;
import org.example.service.concrete_service.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.time.LocalDate;

/*
public class Main {
    static SessionFactory sessionFactory;

    public static void main(String[] args) {
        sessionFactory = new Configuration()
                .addAnnotatedClass(Angajat.class)
                .addAnnotatedClass(Adminsitrator.class)
                .addAnnotatedClass(Cofetar.class)
                .addAnnotatedClass(Vanzator.class)
                .addAnnotatedClass(Produs.class)
                .addAnnotatedClass(ItemComanda.class)
                .addAnnotatedClass(Comanda.class)
                .addAnnotatedClass(ItemVanzare.class)
                .addAnnotatedClass(Vanzare.class)
                .buildSessionFactory();

        Angajat admin = new Adminsitrator("Popescu", "Ioana", "6000615321097", "Suceava", "0743990181");
        admin.setUsername("popescuIoana.1");
        admin.setParola("popescuIoana.1");
        admin.setSalariu(8000f);
        admin.setEmail("popescuIoana@dulcic.ro");
        admin.setDataAngajarii(LocalDate.now());

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Salvare obiect
        session.persist(admin);
        System.out.println("Angajat adăugat: " + admin);

        // Commit și închidere sesiune
        session.getTransaction().commit();
        session.close();

        /*
        Angajat cofetar = new Cofetar("Albu", "Ilinca", "6020711321097", "Suceava", "0722076510");
        cofetar.setUsername("albuIlinca.2");
        cofetar.setParola("albuIlinca.2");
        cofetar.setSalariu(5000f);
        cofetar.setEmail("albuIlinca@dulcic.ro");
        cofetar.setDataAngajarii(LocalDate.now());

        session = sessionFactory.openSession();
        session.beginTransaction();

        // Salvare obiect
        session.persist(cofetar);
        System.out.println("Cofetar adăugat: " + cofetar);

        // Commit și închidere sesiune
        session.getTransaction().commit();
        session.close();*

        // Închidere factory
        sessionFactory.close();
    }
}*/

public class Main extends Application {
    //static SessionFactory sessionFactory;
    public static void main(String[] args) {
        Application.launch(args);

        /*
        sessionFactory = new Configuration()
                .addAnnotatedClass(Angajat.class)
                .addAnnotatedClass(Adminsitrator.class)
                .addAnnotatedClass(Cofetar.class)
                .addAnnotatedClass(Vanzator.class)
                .addAnnotatedClass(Produs.class)
                .addAnnotatedClass(ItemComanda.class)
                .addAnnotatedClass(Comanda.class)
                .addAnnotatedClass(ItemVanzare.class)
                .addAnnotatedClass(Vanzare.class)
                .buildSessionFactory();

        Angajat admin = new Adminsitrator("Popescu", "Ioana", "6000615321097", "Suceava", "0743990181");
        admin.setUsername("popescuIoana.1");
        admin.setParola("popescuIoana.1");
        admin.setSalariu(8000f);
        admin.setEmail("popescuIoana@dulcic.ro");
        admin.setDataAngajarii(LocalDate.now());

        Session session = sessionFactory.openSession();
        session.beginTransaction();

        // Salvare obiect
        session.persist(admin);
        System.out.println("Angajat adăugat: " + admin);

        // Commit și închidere sesiune
        session.getTransaction().commit();
        session.close();

        /*
        Angajat cofetar = new Cofetar("Albu", "Ilinca", "6020711321097", "Suceava", "0722076510");
        cofetar.setUsername("albuIlinca.2");
        cofetar.setParola("albuIlinca.2");
        cofetar.setSalariu(5000f);
        cofetar.setEmail("albuIlinca@dulcic.ro");
        cofetar.setDataAngajarii(LocalDate.now());

        session = sessionFactory.openSession();
        session.beginTransaction();

        // Salvare obiect
        session.persist(cofetar);
        System.out.println("Cofetar adăugat: " + cofetar);

        // Commit și închidere sesiune
        session.getTransaction().commit();
        session.close();

        // Închidere factory
        sessionFactory.close();*/
    }

    @Override
    public void start(Stage stage) throws Exception {
        AdministratorReposiotry repoAdmin = new AdministratorHibernateRepository(Adminsitrator.class, ValidatorFactory.getInstance().getValidator(ValidatorType.ANGAJAT));
        AngajatRepository repoAngajat = new AngajatHibernateRepository(Angajat.class, ValidatorFactory.getInstance().getValidator(ValidatorType.ANGAJAT));
        ProdusRepository repoProdus = new ProdusHibernateRepository(ValidatorFactory.getInstance().getValidator(ValidatorType.PRODUS));
        VanzareRepository vanzareRepository = new VanzareHibernateRepository();
        ComandaRepository comandaRepository = new ComandaHibernateRepository();

        AdministratorService adminService = new ConcreteAdministratorService(repoAdmin);
        AngajatService angajatService = new ConcreteAngajatService(repoAngajat);
        ProdusService produsService = new ConcreteProdusService(repoProdus);
        VanzareService vanzareService = new ConcreteVanzareService(vanzareRepository);
        ComandaService comandaService = new ConcreteComandaService(comandaRepository);

        Service service = new ConcreteService();
        service.setAdministratorService(adminService);
        service.setAngajatService(angajatService);
        service.setProdusService(produsService);
        service.setVanzareService(vanzareService);
        service.setComandaSerice(comandaService);

        vanzareService.addObserver(produsService);

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/fxml/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        LoginController loginController = fxmlLoader.getController();
        loginController.setService(service);

        stage.setTitle("Login");
        stage.setScene(scene);
        stage.show();
    }
}