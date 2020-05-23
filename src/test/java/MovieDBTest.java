import model.Movie;
import model.MovieDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MovieDBTest {

    MovieDB underTest;

    @BeforeEach
    void setUp() throws ParseException {

        underTest = new MovieDB(new Movie("title","2020"));
    }

    @Test
    void testgetMovies() throws ParseException {
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("title","2020"));
        assertEquals(movies, underTest.getMovies());
    }

    @Test
    void testgetMovie() throws ParseException {
        Movie movie = new Movie("title","2020");
        assertEquals(movie, underTest.getMovie("title"));
    }

    @Test
    void testIsMovieInDb() throws ParseException {
        Movie movie = new Movie("title","2020");
        assertEquals(true, underTest.IsMovieInDb(movie));
        Movie movie2 = new Movie("title2","1995");
        assertEquals(false, underTest.IsMovieInDb(movie2));
    }

    @Test
    void testSearch() throws ParseException {
        assertEquals(true, underTest.Search("title"));
        assertEquals(false, underTest.Search("title2"));
    }
}
