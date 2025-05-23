package org.example.controller;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.domain.model.*;
import org.example.service.Service;
import org.example.utils.Formats;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class AdministratorHomeController {
    private Service service;
    private Adminsitrator administrator;
    private Integer idProdus = 0;
    private Integer idAngajat = 0;

    private ObservableList<Angajat> angajatiModel = FXCollections.observableArrayList();
    private ObservableList<Produs> produseModel = FXCollections.observableArrayList();

    @FXML
    private Button adaugaAngajatButton;
    @FXML
    private Button modificaAngajatButton;
    @FXML
    private Button stergeAngajatButton;
    @FXML
    private Button logoutButton1;

    @FXML
    private TableView<Angajat> angajatiTable;
    @FXML
    private TableColumn<Angajat, String> numeAngajatColumn;
    @FXML
    private TableColumn<Angajat, String> prenumeAngajatColumn;
    @FXML
    private TableColumn<Angajat, String> cnpAngajatColumn;
    @FXML
    private TableColumn<Angajat, String> nrTelAngajatColumn;

    @FXML
    private TextField numeAngajatTextField;
    @FXML
    private TextField prenumeAngajatTextField;
    @FXML
    private TextField cnpAngajatTextField;
    @FXML
    private TextField adresaAngajatTextField;
    @FXML
    private TextField nrTelAngajatTextField;
    @FXML
    private TextField emailAngajatTextField;
    @FXML
    private TextField salariuAngajatTextField;
    @FXML
    private TextField usernameAngajatTextField;
    @FXML
    private TextField parolaAngajatTextField;
    @FXML
    private DatePicker dataAngajariiDatePicker;
    @FXML
    private DatePicker dataPlecariiDatePicker;
    @FXML
    private ComboBox<String> rolAngajatComboBox;


    @FXML
    private Button adaugaProdusButton;
    @FXML
    private Button modificaProdusButton;
    @FXML
    private Button stergeProdusButton;
    @FXML
    private Button logoutButton2;

    @FXML
    private TableView<Produs> produseTable;
    @FXML
    private TableColumn<Produs, String> denumireProdusColumn;
    @FXML
    private TableColumn<Produs, Float> pretProdusColumn;
    @FXML
    private TableColumn<Produs, String> umProdusColumn;
    @FXML
    private TableColumn<Produs, String> valabilitateProdusColumn;
    @FXML
    private TableColumn<Produs, String> ingredienteProdusColumn;
    @FXML
    private TableColumn<Produs, String> observatiiProdusColumn;
    @FXML
    private TableColumn<Produs, String> disponibilitateProdusColumn;

    @FXML
    private TextField denumireProdusTextField;
    @FXML
    private TextField pretProdusTextField;
    @FXML
    private TextField umProdusTextField;
    @FXML
    private DatePicker valabilitateProdusDataPicker;
    @FXML
    private TextField ingredienteProdusTextField;
    @FXML
    private TextField observatiiProdusTextField;
    @FXML
    private ComboBox<DisponibilitateProdus> disponibilitateProdusComboBox;
    @FXML
    private TextField stocProdusTextField;

    public void setService(Service service) {
        this.service = service;
        initModel();
    }

    public void setAdministrator(Adminsitrator administrator){
        this.administrator = administrator;
    }

    private void initModel(){
        initProduseModel();
        initAngajatModel();
    }

    private void initProduseModel(){
        var produse = service.getProdusService().findAll();

        produseModel.clear();
        produse.forEach(produseModel::add);
    }

    private void initAngajatModel(){
        var angajati = service.getAngajatService().findAll();

        angajatiModel.clear();
        angajati.forEach(angajat -> {
            if (angajat.getClass() != Adminsitrator.class){
                angajatiModel.add(angajat);
            }
        });
    }

    private void setAngajatiTableViewCellFactory(){
        numeAngajatColumn.setCellValueFactory(new PropertyValueFactory<Angajat, String>("nume"));
        prenumeAngajatColumn.setCellValueFactory(new PropertyValueFactory<Angajat, String>("prenume"));
        cnpAngajatColumn.setCellValueFactory(new PropertyValueFactory<Angajat, String>("cnp"));
        nrTelAngajatColumn.setCellValueFactory(new PropertyValueFactory<Angajat, String>("nrTel"));
    }

    private void setProduseTableViewCellFactory(){
        denumireProdusColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("denumire"));
        pretProdusColumn.setCellValueFactory(new PropertyValueFactory<Produs, Float>("pret"));
        umProdusColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("unitateMasura"));
        valabilitateProdusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataExpriare().format(Formats.dateFormatter)));
        ingredienteProdusColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("ingrediente"));
        observatiiProdusColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("observatii"));
        disponibilitateProdusColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisponibiliateProdus().name()));
    }

    @FXML
    private void initialize(){
        angajatiTable.setItems(angajatiModel);
        produseTable.setItems(produseModel);

        setAngajatiTableViewCellFactory();
        setProduseTableViewCellFactory();

        disponibilitateProdusComboBox.setItems(FXCollections.observableArrayList(DisponibilitateProdus.values()));

        produseTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null){
                idProdus = newSelection.getId();
                updateProdusFields(newSelection);
            }
        });

        angajatiTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) ->{
            if (newSelection != null){
                idAngajat = newSelection.getId();
                updateAngajatFileds(newSelection);
            }
        });

        rolAngajatComboBox.setItems(FXCollections.observableArrayList("Cofetar", "Vanzator"));
    }

    private void updateProdusFields(Produs produs){
        denumireProdusTextField.setText(produs.getDenumire());
        pretProdusTextField.setText(produs.getPret().toString());
        umProdusTextField.setText(produs.getUnitateMasura());
        valabilitateProdusDataPicker.setValue(produs.getDataExpriare());
        ingredienteProdusTextField.setText(produs.getIngrediente());
        observatiiProdusTextField.setText(produs.getObservatii());
        disponibilitateProdusComboBox.setValue(produs.getDisponibiliateProdus());
        stocProdusTextField.setText(produs.getStoc().toString());
    }

    private void updateAngajatFileds(Angajat angajat){
        usernameAngajatTextField.setVisible(true);
        parolaAngajatTextField.setVisible(true);
        dataPlecariiDatePicker.setVisible(true);

        Optional<String> rolAles = rolAngajatComboBox.getItems().stream()
                .filter(rol -> rol.equals(angajat.getClass().getSimpleName()))
                .findFirst();

        rolAles.ifPresent(rolAngajatComboBox::setValue);


        numeAngajatTextField.setText(angajat.getNume());
        prenumeAngajatTextField.setText(angajat.getPrenume());
        cnpAngajatTextField.setText(angajat.getCnp());
        adresaAngajatTextField.setText(angajat.getAdresa());
        nrTelAngajatTextField.setText(angajat.getNrTel());
        emailAngajatTextField.setText(angajat.getEmail());
        usernameAngajatTextField.setText(angajat.getUsername());
        parolaAngajatTextField.setText(angajat.getParola());
        salariuAngajatTextField.setText(angajat.getSalariu().toString());
        dataAngajariiDatePicker.setValue(angajat.getDataAngajarii());
        dataPlecariiDatePicker.setValue(angajat.getDataPlecarii());
    }

    @FXML
    private void handleAdaugaProdusButton(MouseEvent mouseEvent) {
        try {
            stocProdusTextField.setText("50");
            Produs produs = getProdusFromLabels();
            service.getProdusService().save(produs);
            initProduseModel();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());

            alert.setHeaderText(null);
            alert.setTitle("Error");

            alert.show();
        }finally {
            clearProdusTextFields();
        }
    }

    private Produs getProdusFromLabels(){
        String denumire = denumireProdusTextField.getText();
        Float pret = Float.parseFloat(pretProdusTextField.getText());
        String um = umProdusTextField.getText();
        LocalDate dataExpirarii = valabilitateProdusDataPicker.getValue();
        String ingrediente = ingredienteProdusTextField.getText();
        String observatii = observatiiProdusTextField.getText();
        DisponibilitateProdus disponibilitate = disponibilitateProdusComboBox.getSelectionModel().getSelectedItem();
        Integer stoc = Integer.parseInt(stocProdusTextField.getText());

        Produs produs = new Produs(denumire, pret, um, dataExpirarii);
        produs.setIngrediente(ingrediente);
        produs.setObservatii(observatii);
        produs.setDisponibiliateProdus(disponibilitate);
        produs.setStoc(stoc);

        return produs;
    }

    private void clearProdusTextFields(){
        denumireProdusTextField.clear();
        pretProdusTextField.clear();
        umProdusTextField.clear();
        valabilitateProdusDataPicker.setValue(LocalDate.now());
        ingredienteProdusTextField.clear();
        observatiiProdusTextField.clear();
        disponibilitateProdusComboBox.setValue(null);
        stocProdusTextField.clear();
    }

    private void clearAngajatTextFields(){
        rolAngajatComboBox.setValue(null);
        numeAngajatTextField.clear();
        prenumeAngajatTextField.clear();
        cnpAngajatTextField.clear();
        adresaAngajatTextField.clear();
        nrTelAngajatTextField.clear();
        emailAngajatTextField.clear();
        usernameAngajatTextField.clear();
        parolaAngajatTextField.clear();
        salariuAngajatTextField.clear();
        dataAngajariiDatePicker.setValue(null);
        dataPlecariiDatePicker.setValue(null);

        usernameAngajatTextField.setVisible(false);
        parolaAngajatTextField.setVisible(false);
        dataPlecariiDatePicker.setVisible(false);
    }

    @FXML
    private void handleModificaProdusButton(ActionEvent actionEvent) {
        try {
            Produs produs = getProdusFromLabels();
            produs.setId(idProdus);
            service.getProdusService().update(produs);
            initProduseModel();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());

            alert.setHeaderText(null);
            alert.setTitle("Error");

            alert.show();
        }finally {
            clearProdusTextFields();
        }
    }

    public void handleStergeProdusButton(ActionEvent actionEvent) {
        try {
            service.getProdusService().delete(idProdus);
            initProduseModel();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());

            alert.setHeaderText(null);
            alert.setTitle("Error");

            alert.show();
        }finally {
            clearProdusTextFields();
        }
    }

    @FXML
    private void handleLogoutButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
        Parent pane = loader.load();

        LoginController controller = loader.getController();

        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();

        stage.setScene(new Scene(pane));

        controller.setService(service);

        stage.setTitle("Login");
        stage.show();
    }

    @FXML
    private void handleAdaugaAngajatButton(ActionEvent actionEvent) {
        try{
            parolaAngajatTextField.setText("will be generated");
            usernameAngajatTextField.setText("will be generated");
            Angajat angajat = getAngajatFromLabels();
            service.getAngajatService().save(angajat);
            angajatiModel.add(angajat);
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());

            alert.setHeaderText(null);
            alert.setTitle("Error");

            alert.show();
        }finally {
            clearAngajatTextFields();
        }
    }

    private Angajat getAngajatFromLabels(){
        String rolAngajat = rolAngajatComboBox.getSelectionModel().getSelectedItem();
        String nume = numeAngajatTextField.getText();
        String prenume = prenumeAngajatTextField.getText();
        String cnp = cnpAngajatTextField.getText();
        String adresa = adresaAngajatTextField.getText();
        String nrTel = nrTelAngajatTextField.getText();
        String email = emailAngajatTextField.getText();
        Float salariu = Float.parseFloat(salariuAngajatTextField.getText());
        LocalDate dataAnagajarii = dataAngajariiDatePicker.getValue();
        String username = usernameAngajatTextField.getText();
        String parola = parolaAngajatTextField.getText();

        Angajat angajat = null;

        if (rolAngajat.equals("Cofetar")) {
            angajat = new Cofetar(nume, prenume, cnp, adresa, nrTel);
        } else if (rolAngajat.equals("Vanzator")) {
            angajat = new Vanzator(nume, prenume, cnp, adresa, nrTel);
        }

        if (angajat != null) {
            angajat.setEmail(email);
            angajat.setSalariu(salariu);
            angajat.setDataAngajarii(dataAnagajarii);
            angajat.setUsername(username);
            angajat.setParola(parola);
        }

        return angajat;

    }

    @FXML
    private void handleModificaAngajatButton(ActionEvent actionEvent) {
        try{
            Angajat angajat = getAngajatFromLabels();
            angajat.setId(idAngajat);
            service.getAngajatService().update(angajat);
            initAngajatModel();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());

            alert.setHeaderText(null);
            alert.setTitle("Error");

            alert.show();
        }finally {
            clearAngajatTextFields();
        }
    }

    @FXML
    private void handleStergeAngajatButton(ActionEvent actionEvent) {
        try{
            service.getAngajatService().delete(idAngajat);
            initAngajatModel();
        }catch (Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR, e.getMessage());

            alert.setHeaderText(null);
            alert.setTitle("Error");

            alert.show();
        }finally {
            clearAngajatTextFields();
        }
    }
}
