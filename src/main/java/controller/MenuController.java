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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

    private static Logger logger = LoggerFactory.getLogger(MenuController.class);

    @FXML
    public void initialize() throws FileNotFoundException, JAXBException {
        moviesList.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));

        for (Movie movie: movieDB.getMovies()) {
            logger.info("Movie added to moviesList: {}", movie.getTitle());
            moviesList.getItems().add(movie.getTitle());
        }

        favList.getItems().addAll(movieDB.GetFavList(userDB.GetLoggedInUser()));
        logger.info("Favlist loaded.");

        if(userDB.IsLoggedInUserAdmin()) {
            logger.info("Logged in user is admin.");
            admin.setVisible(true);
        }
    }

    public void LogOut(ActionEvent event) throws IOException, JAXBException {
        UserDB userDB = JAXBHelper.fromXML(UserDB.class , new FileInputStream("users.xml"));
        logger.info("UserDB loaded.");
        userDB.LogoutUsers();
        logger.info("Users logged out.");
        JAXBHelper.toXML(userDB, new FileOutputStream("users.xml"));
        logger.info("UserDB saved");

        logger.info("Changing scene.");
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/login.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Login");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public void toAdmin(ActionEvent event) throws IOException {
        logger.info("Changing scene.");
        try{
        Parent root = FXMLLoader.load(Main.class.getResource("/fxml/admin.fxml"));

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.setTitle("Admin Panel");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();}
        catch (IOException e) {
            logger.error("Changing scene error.", e);
            throw e;
        }
    }

    public void Rate(ActionEvent event) throws FileNotFoundException, JAXBException {
        logger.info("Rating movie.");
        try {
            var e = (Button) event.getSource();

            MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class, new FileInputStream("movies.xml"));
            var movie = movieDB.getMovie(movieTitle.getText());

            if (e.equals(voteUp))
                movie.setRating(movie.getRating() + 1);
            else
                movie.setRating(movie.getRating() - 1);

            JAXBHelper.toXML(movieDB, new FileOutputStream("movies.xml"));
        }
        catch (Exception e)
        {
            logger.error("Rating movie error.", e);
            throw e;
        }
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
        logger.info("Searching movie.");
        try{
        MovieDB movieDB = JAXBHelper.fromXML(MovieDB.class , new FileInputStream("movies.xml"));

        moviesList.getItems().clear();
        logger.info("moviesList cleared.");
        moviesList.getItems().addAll(movieDB.Search(searchTitle.getText()));
        logger.info("Added search result to moviesList.");
        }
        catch (Exception e)
        {
            logger.error("Searching movie error.", e);
            throw e;
        }
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
