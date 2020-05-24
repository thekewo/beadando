package model;

import javax.xml.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static java.util.UUID.randomUUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "title", "date", "rating", "fav"})
public class Movie {

    private UUID id;
    private String title;
    private String date;
    private Integer rating;

    private static Logger logger = LoggerFactory.getLogger(Movie.class);

    public String getTitle() {
        logger.info("Got title: {}", title);
        return title;
    }

    public void setTitle(String title) {
        logger.info("Set title: {}", title);
        this.title = title;
    }

    public String getDate() {
        logger.info("Got date: {}", date);
        return date;
    }

    public void setDate(String date) {
        logger.info("Set date: {}", date);
        this.date = date;
    }

    public Integer getRating() {
        logger.info("Got rating: {}", rating);
        return rating;
    }

    public void setRating(Integer rating) {
        logger.info("Set rating: {}", rating);
        this.rating = rating;
    }

    public List<String> getFav() {
        for (String item: fav) {
            logger.info("Fav by user: {}", item);
        }
        return fav;
    }

    public void setFav(List<String> fav) {
        this.fav = fav;
    }

    @XmlElementWrapper(name = "fav")
    @XmlElement(name = "user")
    private List<String> fav = new ArrayList<>();

    public Movie(String user){
        logger.info("User added to fav list: {}", user);
        fav.add(user);
    }

    public  Movie(){}

    public Movie(String title, String date) throws ParseException {
        this.id = randomUUID();
        this.title = title;
        this.date = date;
        this.rating = 0;
        logger.info("Movie created: {}", id.toString());
    }
}
