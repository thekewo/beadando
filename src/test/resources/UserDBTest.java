import model.Movie;
import model.MovieDB;
import model.User;
import model.UserDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserDBTest {

    UserDB underTest;

    @BeforeEach
    void setUp() throws ParseException {

        underTest = new UserDB(new User("username","password"));
    }

    @Test
    void testIsUserInDb() throws ParseException {
        User user = new User("username","password");
        User user2 = new User("username2","password2");
        assertTrue(underTest.IsUserInDb(user));
        assertFalse(underTest.IsUserInDb(user2));
    }

    @Test
    void testGetLoggedInUser() throws ParseException {
        User user = new User("username","password");
        user.setLoggedIn(true);
        underTest.getUser("username").setLoggedIn(true);
        assertEquals(user, underTest.GetLoggedInUser());
    }

    @Test
    void testIsLoggedInUserAdmin() throws ParseException {
        assertFalse(underTest.IsLoggedInUserAdmin());
        underTest.getUser("username").setLoggedIn(true);
        underTest.getUser("username").setAdmin(true);
        assertTrue(underTest.IsLoggedInUserAdmin());
    }

    @Test
    void testLogoutUsers() throws ParseException {
        User user = new User("username","password");
        user.setLoggedIn(true);
        underTest.getUser("username").setLoggedIn(true);
        assertEquals(user, underTest.GetLoggedInUser());
        underTest.LogoutUsers();
        assertNotEquals(user, underTest.GetLoggedInUser());
    }

    @Test
    void testGetAdmins() throws ParseException {
        List<String> list = new ArrayList<>();
        list.add("username");
        list.add("username2");
        underTest.getUser("username").setAdmin(true);
        User user = new User("username2","password2");
        user.setAdmin(true);
        underTest.getUsers().add(user);
        assertEquals(list, underTest.GetAdmins());
    }
}
