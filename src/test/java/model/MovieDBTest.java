package model;

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
        assertEquals(movies.get(0).getTitle(), underTest.getMovies().get(0).getTitle());
    }

    @Test
    void testgetMovie() throws ParseException {
        Movie movie = new Movie("title","2020");
        assertEquals(movie.getTitle(), underTest.getMovie("title").getTitle());
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
        var empty = new ArrayList<String>();
        assertEquals("title", underTest.Search("title").get(0));
        assertEquals(empty, underTest.Search("title2"));
    }
}
