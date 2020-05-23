package model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import static java.util.UUID.randomUUID;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(propOrder = {"id", "title", "date", "rating"})
public class Movie {

    private UUID id;
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getRating() {
        return rating;
    }

    public void setRating(Integer rating) {
        this.rating = rating;
    }

    private Date date;
    private Integer rating;

    public Movie(String title, Integer date) throws ParseException {
        this.id = randomUUID();
        this.title = title;
        this.date = CreateDate(date);
    }

    public Date CreateDate(Integer val) throws ParseException {
        SimpleDateFormat originalFormat = new SimpleDateFormat("yyyy");
        return originalFormat.parse(val.toString());
    }
}
