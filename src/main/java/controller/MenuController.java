package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import jaxb.JAXBHelper;
import main.Main;
import model.Movie;
import model.MovieDB;
import model.UserDB;

import javax.xml.bind.JAXBException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;

public class MenuController {

    @FXML
    public Button admin;
    @FXML
    public TextField movieTitle;
    @FXML
    public Button voteDown;
    @FXML
    public Button voteUp;
    @FXML
    public Text year;
    @FXML
    public Text votes;
    @FXML
    public TextField searchTitle;
    @FXML
    public ListView favList;
    @FXML
    public Button addFavButton;
    @FXML
    private ListView<String> moviesList;

    @FXML
    public void initialize() throws FileNotFoundException, JAXBException {
        moviesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));

        for (Movie movie: movieDB.getMovies()) {
            moviesList.getItems().add(movie.getTitle());
        }

        favList.getItems().addAll(movieDB.GetFavList(userDB.GetLoggedInUser()));

        if(userDB.IsLoggedInUserAdmin())
            admin.setVisible(true);
    }

    public void LogOut(ActionEvent event) throws IOException, JAXBException {
        String resourceName = "user.xml";
        ClassLoader classLoader = getClass().getClassLoader();
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        userDB.LogoutUsers();
        JAXBHelper.toXML(userDB, new FileOutputStream("users.xml"));

        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void toAdmin(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/admin.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Admin Panel");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void Rate(ActionEvent event) throws FileNotFoundException, JAXBException {
        var e = (Button)event.getSource();

        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));
        var movie = movieDB.getMovie(movieTitle.getText());

        if(e.equals(voteUp))
        movie.setRating(movie.getRating()+1);
        else
            movie.setRating(movie.getRating()-1);

        JAXBHelper.toXML(movieDB, new FileOutputStream("movies.xml"));
    }

    public void Click(MouseEvent mouseEvent) throws FileNotFoundException, JAXBException {
        var item = ((ListView<String>)mouseEvent.getSource()).getSelectionModel().getSelectedItem();
        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));
        var movie = movieDB.getMovie(item);

        if(movie != null){
            year.setText(movie.getDate().toString().split("-")[0]);
            votes.setText(movie.getRating().toString());
        }
    }

    public void Search(ActionEvent event) throws FileNotFoundException, JAXBException {
        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));

        moviesList.getItems().clear();
        moviesList.getItems().addAll(movieDB.Search(searchTitle.getText()));
    }

    public void AddFav(ActionEvent event) throws FileNotFoundException, JAXBException {
        var item = moviesList.getSelectionModel().getSelectedItem();
        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        var movie = movieDB.getMovie(item);
        var user = userDB.GetLoggedInUser();

        movie.getFav().add(user.getUsername());

        JAXBHelper.toXML(userDB, new FileOutputStream("users.xml"));
        JAXBHelper.toXML(movieDB, new FileOutputStream("movies.xml"));

        favList.getItems().add(movie.getTitle());
    }
}
