package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import main.Main;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import model.Login;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;
    @FXML
    private TextField password;
    @FXML
    private Button loginButton;
    @FXML
    private Label errorMessage;

    private Login login;

    @FXML
    public void initialize(){
        login = new Login();
    }

    public void Login(ActionEvent event) throws IOException {
        if(login.isUsernameAndPasswordCorrect(username.getText(), password.getText())){
            Parent root = FXMLLoader.load(Main.class.getResource("/fxml/menu.fxml"));

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

            stage.setTitle("Menu");
            stage.setResizable(false);
            stage.setScene(new Scene(root));
            stage.show();
        }
        else {
            errorMessage.setText("Error");
        }
    }

}
