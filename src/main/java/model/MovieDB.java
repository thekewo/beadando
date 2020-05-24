package model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class MovieDB {

    private static Logger logger = LoggerFactory.getLogger(MovieDB.class);

    public List<Movie> getMovies() {
        for (Movie item: movies
             ) {
            logger.info("Movie in movies: {}", item.getTitle());
        }
        return movies;
    }

    public Movie getMovie(String title){
        for (Movie item: movies){
            if(title.equals(item.getTitle())) {
                logger.info("Found movie in movies: {}", title);
                return item;
            }
        }
        logger.info("Did not found movie in movies: {}", title);
        return null;
    }

    @XmlElementWrapper(name = "movies")
    @XmlElement(name = "movie")
    private List<Movie> movies = new ArrayList<>();

    public MovieDB(){
    }

    public MovieDB(Movie movie){
        movies.add(movie);
        logger.info("Movie added to movies: {}", movie.getTitle());
    }

    public boolean IsMovieInDb(Movie movie){
        for (Movie item: movies){
            if(movie.getTitle().equals(item.getTitle())) {
                logger.info("Movie is in db: {}", item.getTitle());
                return true;
            }
        }
        logger.info("Movie is not in db: {}", movie.getTitle());
        return false;
    }

    public List<String> GetFavList(User user){
        var result = new ArrayList<String>();
        for (Movie item: movies){
            if(item.getFav().contains(user.getUsername())) {
                logger.info("User fav this movie: {}, {}", item.getTitle(), user.getUsername());
                result.add(item.getTitle());
            }
        }
        logger.info("Returned fav list.");
        return result;
    }

    public List<String> Search(String s){
        var result = new ArrayList<String>();
        for (Movie item: movies){
            if(item.getTitle().contains(s)) {
                logger.info("Movie contains string: {}, {}", item.getTitle(), s);
                result.add(item.getTitle());
            }
        }
        if(result == null) {
            logger.info("Movie search s is empty.");
            return GetTitles();
        }
        logger.info("Returned movies that contain {}.", s);
        return result;
    }

    public List<String> GetTitles(){
        var result = new ArrayList<String>();
        for (Movie item: movies){
                logger.info("Movie title is: {}", item.getTitle());
                result.add(item.getTitle());
        }
        logger.info("Movie titles returned");
        return result;
    }
}
