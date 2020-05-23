package model;

import javax.xml.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import static java.util.UUID.randomUUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "title", "date", "rating", "fav"})
public class Movie {

    private UUID id;
    private String title;
    private String date;
    private Integer rating;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    public List<String> getFav() {
        return fav;
    }

    public void setFav(List<String> fav) {
        this.fav = fav;
    }

    @XmlElementWrapper(name = "fav")
    @XmlElement(name = "user")
    private List<String> fav = new ArrayList<>();

    public Movie(String user){
        fav.add(user);
    }

    public  Movie(){}

    public Movie(String title, String date) throws ParseException {
        this.id = randomUUID();
        this.title = title;
        this.date = date;
        this.rating = 0;
    }
}
