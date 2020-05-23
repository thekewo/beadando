package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieDB {
    public List<Movie> getMovies() {
        return movies;
    }

    public Movie getMovie(String title){
        for (Movie item: movies){
            if(title.equals(item.getTitle()))
                return item;
        }
        return null;
    }

    @XmlElementWrapper(name = "movies")
    @XmlElement(name = "movie")
    private List<Movie> movies = new ArrayList<>();

    public MovieDB(){
    }

    public MovieDB(Movie movie){
        movies.add(movie);
    }

    public boolean IsMovieInDb(Movie movie){
        for (Movie item: movies){
            if(movie.getTitle().equals(item.getTitle()))
                return true;
        }
        return false;
    }
}
