package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jaxb.JAXBHelper;
import main.Main;
import model.User;
import model.UserDB;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class RegistrationController {
    @FXML
    private Button backButton;
    @FXML
    private Label errorMessage;
    @FXML
    private Button registerButton;
    @FXML
    private TextField password;
    @FXML
    private TextField username;

    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void Register(ActionEvent event) throws Exception {
        var uError = false;
        var pError = false;
        var dbError = false;
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        User user = new User(username.getText(), password.getText());

        errorMessage.setText("");

        if(uError = username.getText().length()<3)
            errorMessage.setText("Username has to be atleast 3 characters.");

        if((pError = password.getText().length()<3) && !uError)
            errorMessage.setText("Password has to be atleast 3 characters.");

        if((dbError = !userDB.IsUserInDb(user)) && !uError && !pError) {
            userDB.getUsers().add(user);
            JAXBHelper.toXML(userDB, new FileOutputStream("users.xml"));
        }
        else {
            if(!dbError && !uError && !pError)
                errorMessage.setText("Username already exists.");
        }
    }
}
