package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jaxb.JAXBHelper;
import main.Main;
import model.Movie;
import model.MovieDB;
import model.User;
import model.UserDB;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

public class AdminController {
    @FXML
    public Button backButton;
    @FXML
    public Button addButton;
    @FXML
    public TextField movieTitle;
    @FXML
    public TextField movieDate;
    @FXML
    public Text errorMessage;
    @FXML
    public ListView userList;
    @FXML
    public Button addUserButton;
    @FXML
    public Button removeUserButton;
    @FXML
    public TextField username;
    @FXML
    public TextField password;
    @FXML
    public TextField searchUsername;
    @FXML
    public Button Search;

    public void initialize() throws FileNotFoundException, JAXBException {
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        for (User user: userDB.getUsers()) {
            userList.getItems().add(user.getUsername());
        }
    }

    public void AddMovie(ActionEvent event) throws FileNotFoundException, JAXBException, ParseException {
        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));
        Movie movie = new Movie(movieTitle.getText(), movieDate.getText());
        errorMessage.setText("");

        if(!movieDB.IsMovieInDb(movie)) {
            movieDB.getMovies().add(movie);
            JAXBHelper.toXML(movieDB, new FileOutputStream("movies.xml"));
        }
        else
            errorMessage.setText("Movie already exists.");
    }

    public void Back(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/menu.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Menu");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void Add(ActionEvent event) throws FileNotFoundException, JAXBException {
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        userDB.getUsers().add(new User(username.getText(), password.getText()));

        JAXBHelper.toXML(userDB, new FileOutputStream("users.xml"));
        Refresh();
    }

    public void Remove(ActionEvent event) throws FileNotFoundException, JAXBException {
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        userDB.getUsers().remove(userDB.getUser(userList.getSelectionModel().getSelectedItem().toString()));

        JAXBHelper.toXML(userDB, new FileOutputStream("users.xml"));
        Refresh();
    }

    private void Refresh() throws FileNotFoundException, JAXBException {
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        userList.getItems().clear();
        userList.getItems().addAll(userDB.GetUsernames());
    }

    public void Search(ActionEvent event) throws FileNotFoundException, JAXBException {
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));

        userList.getItems().clear();
        userList.getItems().addAll(userDB.Search(searchUsername.getText()));
    }
}
