package org.example.controller.utils;

import javafx.scene.control.Alert;

public class ShowAlert {
    public static void showErrorAlert(String message){
        var alert = new Alert(Alert.AlertType.ERROR, message);

        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
