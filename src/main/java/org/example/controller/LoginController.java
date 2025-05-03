package org.example.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import org.example.domain.model.Adminsitrator;
import org.example.domain.model.Cofetar;
import org.example.domain.model.Vanzator;
import org.example.service.Service;
import org.example.service.exception.ServiceException;

import java.io.IOException;


public class LoginController {
    private Service service;

    @FXML
    private Button loginButton;

    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField passwordTextField;

    @FXML
    private Label errorMessageLabel;

    public void setService(Service service){
        this.service = service;
    }

    @FXML
    private void handleLoginButton(MouseEvent mouseEvent){
        errorMessageLabel.setVisible(false);
        String username = usernameTextField.getText();
        try {
            var angajat = service.login(username);
            String password = passwordTextField.getText();
            if (!password.equals(angajat.getParola())){
                passwordTextField.clear();
                errorMessageLabel.setText("Parola incorecta!\n");
                errorMessageLabel.setVisible(true);
            } else{
                if (angajat.getClass() == Adminsitrator.class){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/administrator_home.fxml"));
                    Parent pane = loader.load();

                    AdministratorHomeController controller = loader.getController();

                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

                    stage.setScene(new Scene(pane));

                    controller.setService(service);
                    controller.setAdministrator((Adminsitrator) angajat);

                    stage.setTitle("Home");
                    stage.show();
                } else if (angajat.getClass() == Cofetar.class) {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/cofetar_home.fxml"));
                    Parent pane = loader.load();

                    CofetarHomeController controller = loader.getController();

                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

                    stage.setScene(new Scene(pane));

                    controller.setService(service);
                    controller.setCofetar((Cofetar) angajat);

                    stage.setTitle("Cofetar");
                    stage.show();
                } else if (angajat.getClass() == Vanzator.class){
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/vanzator_home.fxml"));
                    Parent pane = loader.load();

                    VanzatorHomeController controller = loader.getController();

                    Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();

                    stage.setScene(new Scene(pane));

                    controller.setService(service);
                    controller.setVanzator((Vanzator) angajat);

                    stage.setTitle("Vanzator");
                    stage.show();
                }
            }
        }catch (ServiceException serviceException){
            usernameTextField.clear();
            passwordTextField.clear();
            errorMessageLabel.setText(serviceException.getMessage());
            errorMessageLabel.setVisible(true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
