import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;

import static org.junit.jupiter.api.Assertions.*;

public class UserTest {
    User underTest;

    @BeforeEach
    void setUp() throws ParseException {

        underTest = new User("username", "password");
    }

    @Test
    void testgetUsername() {
        assertEquals("username", underTest.getUsername());
    }

    @Test
    void testgetPassword() {
        assertEquals("password", underTest.getPassword());
    }

    @Test
    void testisLoggedIn() {
        assertEquals(false, underTest.isLoggedIn());
    }

    @Test
    void testsetLoggedIn() {
        underTest.setLoggedIn(true);
        assertEquals(true, underTest.isLoggedIn());
    }

    @Test
    void testsetUsername() {
        underTest.setUsername("newUsername");
        assertEquals("newUsername", underTest.getUsername());
    }
}
