package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class MovieTest {

    Movie underTest;

    @BeforeEach
    void setUp() throws ParseException {

        underTest = new Movie("title", "2020");
    }

    @Test
    void testgetTitle() {
        assertEquals("title", underTest.getTitle());
    }

    @Test
    void testgetDate() {
        assertEquals("2020", underTest.getDate());
    }

    @Test
    void testgetRating() {
        assertEquals(0, underTest.getRating());
    }

    @Test
    void testsetTitle() {
        underTest.setTitle("newTitle");
        assertEquals("newTitle", underTest.getTitle());
    }

    @Test
    void testgetFav() {
        underTest.getFav().add("testUser");
        assertEquals("testUser", underTest.getFav().get(0));
    }
}
