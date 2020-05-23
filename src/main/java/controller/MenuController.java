package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import main.Main;
import model.User;

import java.io.IOException;
import java.util.List;

public class MenuController {

    @FXML
    private Button logoutButton;


    public void LogOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

}
