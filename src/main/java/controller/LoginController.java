package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import jaxb.JAXBHelper;
import main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import model.UserDB;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class LoginController {

    @FXML
    public Button registerButton;
    @FXML
    public Button closeButton;
    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorMessage;


    public void Login(ActionEvent event) throws Exception {
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        var user = userDB.getUser(username.getText());
        errorMessage.setText("");

        if(user == null)
            errorMessage.setText("Incorrect username.");
        else if(!password.getText().equals(user.getPassword())){
            errorMessage.setText("Incorrect password.");
        }
        else{
            userDB.getUser(user.getUsername()).setLoggedIn(true);
            JAXBHelper.toXML(userDB, new FileOutputStream("users.xml"));

            Parent root = FXMLLoader.load(Main.class.getResource("/fxml/menu.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Menu");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }
    }

    public void Register(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/registration.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Register");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void Close(ActionEvent event) {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();
    }
}
