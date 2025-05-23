package org.example.controller;

import jakarta.validation.constraints.Null;
import javafx.beans.property.SimpleObjectProperty;
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
import javafx.stage.Stage;
import org.example.controller.utils.ShowAlert;
import org.example.domain.model.*;
import org.example.service.Service;
import org.example.service.exception.ServiceException;
import org.example.utils.Formats;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VanzatorHomeController {
    private Service service;
    private Vanzator vanzator;
    private Integer idProdusVanzare;
    private Integer idProdusComanda;

    private ObservableList<ItemVanzare> itemiVanzareModel = FXCollections.observableArrayList();
    private ObservableList<Produs> produseVanzareModel = FXCollections.observableArrayList();
    private ObservableList<Produs> produseComandaModel = FXCollections.observableArrayList();
    private ObservableList<ItemComanda> itemiComandaModel = FXCollections.observableArrayList();
    private ObservableList<Comanda> comenziModel = FXCollections.observableArrayList();

    private final static DecimalFormat decimalFormat = new DecimalFormat("#.00");

    //-----------Vanzare tab------------
    @FXML
    private Tab vanzariTab;

    @FXML
    private TableView<Produs> produseVanzareTable;
    @FXML
    private TableColumn<Produs, String> denumireProdusVanzareColumn;
    @FXML
    private TableColumn<Produs, Float> pretProdusVanzareColumn;
    @FXML
    private TableColumn<Produs, String> umProdusVanzareColumn;
    @FXML
    private TableColumn<Produs, String> valabilitateProdusVanzareColumn;
    @FXML
    private TableColumn<Produs, String> ingredienteProdusVanzareColumn;
    @FXML
    private TableColumn<Produs, String> observatiiProdusVanzareColumn;
    @FXML
    private TableColumn<Produs, String> disponibilitateProdusVanzareColumn;

    @FXML
    private TableView<ItemVanzare> cosVanzareTable;
    @FXML
    private TableColumn<ItemVanzare, String> denumireItemVanzareColumn;
    @FXML
    private TableColumn<ItemVanzare, Float> cantitateItemVazareColumn;
    @FXML
    private TableColumn<ItemVanzare, Float> pretItemVanzareColumn;

    @FXML
    private TextField denumireProdusVanzareTextField;
    @FXML
    private TextField pretProdusVanzareTextField;
    @FXML
    private TextField cantitateProdusVanzareTextField;
    @FXML
    private TextField stocProdusVanzareTextField;

    @FXML
    private Button logoutVanzareButton;
    @FXML
    private Button adaugaItemVanzareButton;
    @FXML
    private Button modificaItemVanzareButton;
    @FXML
    private Button stergeItemVanzareButton;
    @FXML
    private Button plataVanzareButton;

    @FXML
    private Label totalCosVanzareValue;

    //---------------Comanda tab-----------------

    @FXML
    private Tab comenziTab;

    private boolean comenziTabInitialized = false;

    @FXML
    private TableView<Produs> produseComandaTable;
    @FXML
    private TableColumn<Produs, String> denumireProdusComandaColumn;
    @FXML
    private TableColumn<Produs, Float> pretProdusComandaColumn;
    @FXML
    private TableColumn<Produs, String> umProdusComandaColumn;
    @FXML
    private TableColumn<Produs, String> valabilitateProdusComandaColumn;
    @FXML
    private TableColumn<Produs, String> ingredienteProdusComandaColumn;
    @FXML
    private TableColumn<Produs, String> observatiiProdusComandaColumn;
    @FXML
    private TableColumn<Produs, String> disponibilitateProdusComandaColumn;

    @FXML
    private TableView<ItemComanda> cosComandaTable;
    @FXML
    private TableColumn<ItemComanda, String> denumireItemComandaColumn;
    @FXML
    private TableColumn<ItemComanda, Float> cantitateItemComandaColumn;
    @FXML
    private TableColumn<ItemComanda, Float> pretItemComandaColumn;

    @FXML
    private TableView<Comanda> comenziTable;

    @FXML
    private TableColumn<Comanda, String> numeClientColumn;
    @FXML
    private TableColumn<Comanda, String> nrTelClientColumn;
    @FXML
    private TableColumn<Comanda, StatusComanda> statusComandaColumn;

    @FXML
    private ChoiceBox<StatusComanda> statusComandaChoiceBox;

    @FXML
    private CheckBox termeniConditiiCheckBox;

    @FXML
    private TextField denumireProdusComandaTextField;
    @FXML
    private TextField pretProdusComandaTextField;
    @FXML
    private TextField cantitateProdusComandaTextField;
    @FXML
    private TextField obsComandaTextField;

    @FXML
    private TextField numeClientTextField;
    @FXML
    private TextField nrTelClientTextField;
    @FXML
    private DatePicker dataPreluareComandaDatePicker;
    @FXML
    private DatePicker dataRidicareComandaDatePicker;
    @FXML
    private TextField avansComandaTextField;

    @FXML
    private Button logoutComandaButton;
    @FXML
    private Button adaugaItemComandaButton;
    @FXML
    private Button modificaItemComandaButton;
    @FXML
    private Button stergeItemComandaButton;
    @FXML
    private Button plataComandaButton;

    @FXML
    private Label totalCosComandaValue;
    @FXML
    private Label avansComandaLabel;
    @FXML
    private Label restPlataComanda;


    public void setService(Service service) {
        this.service = service;
        initVanzariTabModel();
    }

    public void setVanzator(Vanzator vanzator) {
        this.vanzator = vanzator;
    }

    /*
    private void initModel(){
        initProduseVanzareModel();
    }*/

    private void initVanzariTabModel(){
        initProduseVanzareModel();
    }

    private void initComenziTabModel(){
        initProduseComandaModel();
        initStatusComandaChoiceBox();
    }

    private void initProduseVanzareModel(){
        var produse = service.getProdusService().filterByDisponibilitate(DisponibilitateProdus.VANZARE);

        produseVanzareModel.clear();
        produse.forEach(produseVanzareModel::add);
    }

    private void initStatusComandaChoiceBox(){
        ObservableList<StatusComanda> statusComandaModel =  FXCollections.observableArrayList(StatusComanda.values());

        statusComandaChoiceBox.setItems(statusComandaModel);

        statusComandaChoiceBox.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if (newSelection != null){
                initComenziModel();
            }
        });
    }

    private void initProduseComandaModel(){
        var produse = service.getProdusService().findAll();

        produseComandaModel.clear();
        produse.forEach(produseComandaModel::add);
    }

    private void initComenziModel(){
        var selectedStatus = statusComandaChoiceBox.getSelectionModel().getSelectedItem();

        var comenzi = service.getComandaService().filterByStatus(selectedStatus);

        comenziModel.clear();

        comenzi.forEach(comenziModel::add);
    }

    private void setProduseVanzariTableViewCellFactory(){
        denumireProdusVanzareColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("denumire"));
        pretProdusVanzareColumn.setCellValueFactory(new PropertyValueFactory<Produs, Float>("pret"));
        umProdusVanzareColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("unitateMasura"));
        valabilitateProdusVanzareColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataExpriare().format(Formats.dateFormatter)));
        ingredienteProdusVanzareColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("ingrediente"));
        observatiiProdusVanzareColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("observatii"));
        disponibilitateProdusVanzareColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisponibiliateProdus().name()));
    }

    private void setProduseComandaTableViewCellFactory(){
        denumireProdusComandaColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("denumire"));
        pretProdusComandaColumn.setCellValueFactory(new PropertyValueFactory<Produs, Float>("pret"));
        umProdusComandaColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("unitateMasura"));
        valabilitateProdusComandaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDataExpriare().format(Formats.dateFormatter)));
        ingredienteProdusComandaColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("ingrediente"));
        observatiiProdusComandaColumn.setCellValueFactory(new PropertyValueFactory<Produs, String>("observatii"));
        disponibilitateProdusComandaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getDisponibiliateProdus().name()));
    }

    private void setCosVanzariTableViewCellFactory(){
        denumireItemVanzareColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdus().getDenumire()));
        cantitateItemVazareColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantitate()));
        pretItemVanzareColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProdus().getPret()));
    }

    private void setCosComandaTableViewCellFactory(){
        denumireItemComandaColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getProdus().getDenumire()));
        cantitateItemComandaColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getCantitate()));
        pretItemComandaColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getProdus().getPret()));
    }

    private void setComenziTableViewCellFactory(){
        numeClientColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNumeClient()));
        nrTelClientColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getNrTelClient()));
        statusComandaColumn.setCellValueFactory(cellData -> new SimpleObjectProperty<>(cellData.getValue().getStatusComanda()));
    }

    private void initVanzariTab(){
        setProduseVanzariTableViewCellFactory();

        produseVanzareTable.setItems(produseVanzareModel);

        produseVanzareTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if (newSelection != null){
                idProdusVanzare = newSelection.getId();
                updateItemVanzareFieldsFromProduseTable(newSelection);
                if (!cosVanzareTable.getSelectionModel().isEmpty()) {
                    cosVanzareTable.getSelectionModel().clearSelection();
                }
            }
        });

        cosVanzareTable.setItems(itemiVanzareModel);

        setCosComandaTableViewCellFactory();

        cosVanzareTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if (newSelection != null){
                updateItemVanzareFieldsFromItemiVanzareTable(newSelection);
                if (!produseVanzareTable.getSelectionModel().isEmpty()) {
                    produseVanzareTable.getSelectionModel().clearSelection();
                }
            }
        });
    }

    private void initComenziTab(){
        comenziTab.setOnSelectionChanged(event -> {
            if (comenziTab.isSelected() && !comenziTabInitialized){
                comenziTabInitialized = true;

                initComenziTabModel();
                setProduseComandaTableViewCellFactory();

                produseComandaTable.setItems(produseComandaModel);

                produseComandaTable.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) ->{
                    if (newValue != null){
                        updateItemComandaFieldsFromProduseTable(newValue);

                        if (!produseComandaTable.getSelectionModel().isEmpty()){
                            cosComandaTable.getSelectionModel().clearSelection();
                        }
                    }
                });
            }
        });

        setCosVanzariTableViewCellFactory();

        cosComandaTable.setItems(itemiComandaModel);

        cosComandaTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if (newSelection != null){
                updateItemComandaFieldsFromItemiComandaTable(newSelection);

                if (!produseComandaTable.getSelectionModel().isEmpty()){
                    produseComandaTable.getSelectionModel().clearSelection();
                }
            }
        });

        totalCosComandaValue.setText("0");
        avansComandaLabel.setText("0");
        restPlataComanda.setText("0");

        setComenziTableViewCellFactory();
        comenziTable.setItems(comenziModel);

        comenziTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection)->{
            if (newSelection != null){
                updateDetaliiComandaTextFieldsFromComezniTable(newSelection);
            }
        });
    }

    @FXML
    private void initialize(){
        initVanzariTab();
        initComenziTab();
    }

    private void updateItemVanzareFieldsFromProduseTable(Produs produs){
        denumireProdusVanzareTextField.setText(produs.getDenumire());
        pretProdusVanzareTextField.setText(produs.getPret().toString());
        stocProdusVanzareTextField.setText(produs.getStoc().toString());
        cantitateProdusVanzareTextField.setText("1");
    }

    private void updateItemVanzareFieldsFromItemiVanzareTable(ItemVanzare itemVanzare){
        denumireProdusVanzareTextField.setText(itemVanzare.getProdus().getDenumire());
        pretProdusVanzareTextField.setText(itemVanzare.getProdus().getPret().toString());
        stocProdusVanzareTextField.setText(itemVanzare.getProdus().getStoc().toString());
        cantitateProdusVanzareTextField.setText(itemVanzare.getCantitate().toString());
    }

    private void updateItemComandaFieldsFromProduseTable(Produs produs){
        denumireProdusComandaTextField.setText(produs.getDenumire());
        pretProdusComandaTextField.setText(produs.getPret().toString());
        cantitateProdusComandaTextField.setText("1");
        obsComandaTextField.clear();
    }

    private void updateItemComandaFieldsFromItemiComandaTable(ItemComanda itemVanzare){
        denumireProdusComandaTextField.setText(itemVanzare.getProdus().getDenumire());
        pretProdusComandaTextField.setText(itemVanzare.getProdus().getPret().toString());
        cantitateProdusComandaTextField.setText(itemVanzare.getCantitate().toString());
        obsComandaTextField.setText(itemVanzare.getObservatii());
    }

    private void updateDetaliiComandaTextFieldsFromComezniTable(Comanda comanda){
        var produseComandate = comanda.getProduseComandate();

        itemiComandaModel.clear();
        itemiComandaModel.addAll(produseComandate);

        numeClientTextField.setText(comanda.getNumeClient());
        nrTelClientTextField.setText(comanda.getNrTelClient());
        dataRidicareComandaDatePicker.setValue(comanda.getDataRidicarii().toLocalDate());
        dataPreluareComandaDatePicker.setValue(comanda.getDataPreluarii().toLocalDate());

        var avans = comanda.getAvans();
        var total = comanda.getPret();
        var rest = total - avans;

        avansComandaTextField.setText(String.valueOf(avans));
        totalCosComandaValue.setText(String.valueOf(total));
        restPlataComanda.setText(String.valueOf(rest));
        avansComandaLabel.setText(String.valueOf(calculateAvans(total)));
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
    }


    @FXML
    private void handleAdaugaItemVanzareButton(ActionEvent actionEvent) {
        try {
            var selectedProdus = produseVanzareTable.getSelectionModel().getSelectedItem();
            if (selectedProdus == null){
                throw new ServiceException("Trebuie să selectați un pordus din catalog!\n");
            }

            if (Float.parseFloat(cantitateProdusVanzareTextField.getText()) < 0){
                throw new ServiceException("Cantitatea nu poate fi negativă!");
            }
            verificaStoc();

            var cantitate = Float.parseFloat(cantitateProdusVanzareTextField.getText());
            selectedProdus.setStoc((int) (selectedProdus.getStoc() - cantitate));

            var itemVanzare = new ItemVanzare(selectedProdus, cantitate);

            var oldTotal = Float.parseFloat(totalCosVanzareValue.getText());
            var newTotal = selectedProdus.getPret() * cantitate + oldTotal;
            totalCosVanzareValue.setText(Float.toString(newTotal));

            itemiVanzareModel.add(itemVanzare);
        }catch (ServiceException serviceException){
            ShowAlert.showErrorAlert(serviceException.getMessage());
        }
    }

    private void verificaStoc(){
        var selectedProdus = produseVanzareTable.getSelectionModel().getSelectedItem();
        if (selectedProdus == null){
            selectedProdus = cosVanzareTable.getSelectionModel().getSelectedItem().getProdus();
        }
        var cantitate = Integer.parseInt(cantitateProdusVanzareTextField.getText());

        if (cantitate > selectedProdus.getStoc()){
            throw new ServiceException("Stocul este insuficient (doar " + selectedProdus.getStoc() + ")!");
        }
    }

    @FXML
    private void handleModificaItemVanzareButton(ActionEvent actionEvent) {
        try{
            var selectedItemVanzare = cosVanzareTable.getSelectionModel().getSelectedItem();

            if (selectedItemVanzare == null){
                throw new ServiceException("Trebuie să selectați un item din coș!\n");
            }

            if (Float.parseFloat(cantitateProdusVanzareTextField.getText()) < 0){
                throw new ServiceException("Cantitatea nu poate fi negativă!");
            }
            verificaStoc();

            var cantitateVeche = selectedItemVanzare.getCantitate();
            var cantitateNoua = Float.parseFloat(cantitateProdusVanzareTextField.getText());
            var stoc = selectedItemVanzare.getProdus().getStoc();
            var totalVechi = Float.parseFloat(totalCosVanzareValue.getText());

            if (cantitateVeche < cantitateNoua){
                selectedItemVanzare.getProdus().setStoc((int) (stoc - (cantitateNoua - cantitateVeche)));
                totalCosVanzareValue.setText(String.valueOf((totalVechi + (cantitateNoua - cantitateVeche) * selectedItemVanzare.getProdus().getPret())));
            } else if (cantitateVeche > cantitateNoua) {
                selectedItemVanzare.getProdus().setStoc((int) (stoc + cantitateVeche - cantitateNoua));
                totalCosVanzareValue.setText(String.valueOf(totalVechi - ((cantitateVeche - cantitateNoua) * selectedItemVanzare.getProdus().getPret())));
            }

            selectedItemVanzare.setCantitate(cantitateNoua);

        }catch (ServiceException serviceException){
            ShowAlert.showErrorAlert(serviceException.getMessage());
        }
    }

    @FXML
    private void handlePlatesteVanzareButton(ActionEvent actionEvent) {
        var alert = new Alert(Alert.AlertType.CONFIRMATION, "Plată confirmată?");

        alert.setTitle("Confirmare");
        alert.setHeaderText(null);

        Optional<ButtonType> option = alert.showAndWait();

        if (option.isPresent() && option.get() == ButtonType.OK){
            try{
                List<ItemVanzare> produseVandute = new ArrayList<>(cosVanzareTable.getItems());
                var vaznare = new Vanzare(LocalDateTime.now(), produseVandute);
                vaznare.setVanzator(vanzator);

                service.getVanzareService().save(vaznare);

                var alertOk = new Alert(Alert.AlertType.INFORMATION, "Comandă realizată cu succes!");

                alertOk.setTitle("OK");
                alertOk.setHeaderText(null);
                alertOk.show();

                clearVanzareTextFieldsAndTotal();
                itemiVanzareModel.clear();
            }catch (Exception exception){
                ShowAlert.showErrorAlert(exception.getMessage());
            }
        }
    }

    private void clearVanzareTextFieldsAndTotal(){
        denumireProdusVanzareTextField.clear();
        pretProdusVanzareTextField.clear();
        cantitateProdusVanzareTextField.clear();
        stocProdusVanzareTextField.clear();
        totalCosVanzareValue.setText("0");
    }

    private void clearComandaTextFieldsAndTotal(){
        denumireProdusComandaTextField.clear();
        pretProdusComandaTextField.clear();
        cantitateProdusComandaTextField.clear();
        obsComandaTextField.clear();
        totalCosComandaValue.setText("0");
    }

    @FXML
    private void handleStergeItemVanzareButton(ActionEvent actionEvent) {
        try{
            var selectedItemVanzare = cosVanzareTable.getSelectionModel().getSelectedItem();

            if (selectedItemVanzare == null){
                throw new ServiceException("Trebuie să selectați un pordus din coș!\n");
            }

            var produsItemVanzare = selectedItemVanzare.getProdus();

            var stoc = selectedItemVanzare.getCantitate() + produsItemVanzare.getStoc();

            produsItemVanzare.setStoc((int) stoc);

            var totalVechi = Float.parseFloat(totalCosVanzareValue.getText());
            totalCosVanzareValue.setText(String.valueOf(totalVechi - produsItemVanzare.getPret() * selectedItemVanzare.getCantitate()));

            itemiVanzareModel.remove(selectedItemVanzare);
        }catch (Exception exception){
            ShowAlert.showErrorAlert(exception.getMessage());
        }
    }

    @FXML
    private void handleAdaugaItemComandaButton(ActionEvent actionEvent) {
            try{
                var itemComandaProdus = produseComandaTable.getSelectionModel().getSelectedItem();
                if (itemComandaProdus == null){
                    throw new ServiceException("Trebuie să selectați un produs din catalogul produselor!\n");
                }

                var denumire = denumireProdusComandaTextField.getText();
                var pret = Float.parseFloat(pretProdusComandaTextField.getText());
                var cantitate = Float.parseFloat(cantitateProdusComandaTextField.getText());
                var observatii = obsComandaTextField.getText();

                var itemComanda = new ItemComanda(itemComandaProdus, cantitate);
                itemComanda.setObservatii(observatii);

                itemiComandaModel.add(itemComanda);

                var totalVechi = Float.parseFloat(totalCosComandaValue.getText());

                var total = totalVechi + pret * cantitate;
                var avans = calculateAvans(total);
                var rest = calculateRestPlata(total, avans);

                totalCosComandaValue.setText(String.valueOf(total));
                avansComandaLabel.setText(String.valueOf(avans));
                restPlataComanda.setText(String.valueOf(rest));

            } catch (Error | Exception e) {
                ShowAlert.showErrorAlert(e.getLocalizedMessage());
            }
    }

    @FXML
    private void handleModificaItemComandaButton(ActionEvent actionEvent) {
        try{
            var selectedItemComanda = cosComandaTable.getSelectionModel().getSelectedItem();

            if (selectedItemComanda == null){
                throw new ServiceException("Trebuie să selectați un produs din lista itemilor din comandă!\n");
            }

            var cantitateNoua = Float.parseFloat(cantitateProdusComandaTextField.getText());
            var observatii = obsComandaTextField.getText();

            var total = Float.parseFloat(totalCosComandaValue.getText());
            var cantitateVeche = selectedItemComanda.getCantitate();

            if (cantitateNoua > cantitateVeche){
                total += (cantitateNoua - cantitateVeche) * selectedItemComanda.getProdus().getPret();
            } else if (cantitateNoua < cantitateVeche) {
                total -= (cantitateVeche - cantitateNoua) * selectedItemComanda.getProdus().getPret();
            }

            var avans = calculateAvans(total);
            var rest = calculateRestPlata(total, avans);

            selectedItemComanda.setCantitate(cantitateNoua);
            selectedItemComanda.setObservatii(observatii);

            totalCosComandaValue.setText(String.valueOf(total));
            avansComandaLabel.setText(String.valueOf(avans));
            restPlataComanda.setText(String.valueOf(rest));

        }catch (Exception e){
            ShowAlert.showErrorAlert(e.getMessage());
        }
    }

    @FXML
    private void handleStergeItemComandaButton(ActionEvent actionEvent) {
        try{
            var selectedItemComanda = cosComandaTable.getSelectionModel().getSelectedItem();

            if (selectedItemComanda == null){
                throw new ServiceException("Trebuie să selectați un item din coș!\n");
            }

            var total = Float.parseFloat(totalCosComandaValue.getText()) - selectedItemComanda.getCantitate() * selectedItemComanda.getProdus().getPret();
            var avans = calculateAvans(total);
            var rest = calculateRestPlata(total, avans);

            totalCosComandaValue.setText(String.valueOf(total));
            avansComandaLabel.setText(String.valueOf(avans));
            restPlataComanda.setText(String.valueOf(rest));

            itemiComandaModel.remove(selectedItemComanda);

        }catch (Exception e){
            ShowAlert.showErrorAlert(e.getMessage());
        }
    }

    @FXML
    private void handleFinalizeazaComandaButton(ActionEvent actionEvent) {
        try {
            var avans = Float.parseFloat(avansComandaTextField.getText());
            var avansNecesar = Float.parseFloat(avansComandaLabel.getText());
            if (avans < avansNecesar){
                throw new ServiceException("Avansul trebuie să fie minim " + avansNecesar + "!\n");
            }

            var alert = new Alert(Alert.AlertType.CONFIRMATION, "Plată confirmată?");

            alert.setTitle("Confirmare");
            alert.setHeaderText(null);

            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                if (!termeniConditiiCheckBox.isSelected()) {
                    throw new ServiceException("Termenii și Condițiile trebuie acceptate!\n");
                }
                var nume = numeClientTextField.getText();
                var nrTel = nrTelClientTextField.getText();
                var dataPreluare = LocalDateTime.now();
                var dataRidicare = dataRidicareComandaDatePicker.getValue();
                var pret = Float.parseFloat(totalCosComandaValue.getText());

                List<ItemComanda> itemiComanda = new ArrayList<>(cosComandaTable.getItems());
                var comanda = new Comanda(nume, nrTel, dataPreluare, pret, itemiComanda);
                comanda.setAvans(avans);
                comanda.setPret(pret);
                comanda.setDataRidicarii(dataRidicare.atStartOfDay());

                List<Vanzator> vanzatori = new ArrayList<>();
                vanzatori.add(vanzator);

                comanda.setVanzatori(vanzatori);

                service.getComandaService().save(comanda);

                var alertOk = new Alert(Alert.AlertType.INFORMATION, "Comandă adăugată cu succes!\n");

                alertOk.setTitle("Confirmare");
                alertOk.setHeaderText(null);
                alertOk.showAndWait();

                clearInformatiiSuplimentareComanda();
                clearComandaTextFieldsAndTotal();
            }
        } catch (Exception exception) {
            ShowAlert.showErrorAlert(exception.getMessage());
        }
    }

    private Float calculateAvans(Float total){
        return Float.valueOf(decimalFormat.format(0.3F * total));
    }

    private Float calculateRestPlata(Float total, Float platit){
        return Float.valueOf(decimalFormat.format(total - platit));
    }

    private void clearInformatiiSuplimentareComanda(){
        numeClientTextField.clear();
        nrTelClientTextField.clear();
        dataPreluareComandaDatePicker.getEditor().clear();
        dataRidicareComandaDatePicker.getEditor().clear();
        avansComandaTextField.clear();
    }
}
