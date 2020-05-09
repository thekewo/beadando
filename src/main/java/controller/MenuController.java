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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.IOException;
import java.util.List;

public class MenuController {

    @FXML
    private Button logoutButton;

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("beadando-mysql");

    public void LogOut(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public List<User> GetUsers(ActionEvent event) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT l FROM sql7339094 l ORDER BY l.username;", User.class).getResultList();
        } finally {
            em.close();
        }
    }
}
