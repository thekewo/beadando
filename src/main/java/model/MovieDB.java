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

    public List<String> GetFavList(User user){
        var result = new ArrayList<String>();
        for (Movie item: movies){
            if(item.getFav().contains(user.getUsername()))
                result.add(item.getTitle());
        }
        return result;
    }

    public List<String> Search(String s){
        var result = new ArrayList<String>();
        for (Movie item: movies){
            if(item.getTitle().contains(s))
                result.add(item.getTitle());
        }
        if(result == null)
            return GetTitles();

        return result;
    }

    public List<String> GetTitles(){
        var result = new ArrayList<String>();
        for (Movie item: movies){
                result.add(item.getTitle());
        }
        return result;
    }
}
