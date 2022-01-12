package com.fifteen;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import com.fifteen.database.DBMethod;
import com.fifteen.database.User;

import org.junit.jupiter.api.*;

/**
 * Unit test for the User sign up function from User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest 
{
    User newUser;
    static private final String EMAIL = "A";
    static private final String USERNAME = "A";
    static private final int IS_ADMIN = 1;

    @BeforeAll
    public void setUpNewuser() {
        String email = EMAIL;
        String username= USERNAME;
        String textPassword= "a";
        //User Id 
        int isAdmin = IS_ADMIN;
        newUser = User.createUserFromSignUp(email, username, textPassword, isAdmin);
    }

    @Test
    public void shouldReturncorrectEmail()
    {
        assertEquals(newUser.getEmail(), EMAIL);
    }

    @Test
    public void shouldReturncorrectUsername()
    {
        assertEquals(newUser.getUsername(), USERNAME);
    }

    @Test
    public void shouldReturncorrectHashedPassword()
    {
        assertNotEquals(newUser.getHashedPassword(), "S");
    }

    @Test
    public void shouldReturncorrectisAdmin()
    {
        assertEquals(newUser.getIsAdmin(), IS_ADMIN);
    }

    @Test
    public void shouldreturnNonNaId()
    {
        assertNotEquals(newUser.getUserID(), "NA");
    }

    @AfterAll
    public void closeConnection() {
        try {
            DBMethod.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
