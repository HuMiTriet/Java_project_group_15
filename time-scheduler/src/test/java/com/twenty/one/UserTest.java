package com.twenty.one;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.SQLException;

import com.twenty.one.database.DBMethod;
import com.twenty.one.database.User;

import org.junit.jupiter.api.*;

/**
 * Unit test for the User sign up function from User
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserTest 
{
    User newUser;

    @BeforeAll
    public void setUpNewuser() {
        String email = "S";
        String username= "S";
        String textPassword= "S";
        //User Id 
        int isAdmin = 1;
        newUser = User.createUserFromSignUp(email, username, textPassword, isAdmin);
    }

    @Test
    public void shouldReturncorrectEmail()
    {
        assertEquals(newUser.getEmail(), "S");
    }

    @Test
    public void shouldReturncorrectUsername()
    {
        assertEquals(newUser.getUsername(), "S");
    }

    @Test
    public void shouldReturncorrectHashedPassword()
    {
        assertNotEquals(newUser.getHashedPassword(), "S");
    }

    @Test
    public void shouldReturncorrectisAdmin()
    {
        assertEquals(newUser.getIsAdmin(), 1);
    }

//    @Test
//    public void shouldreturnNonNaId()
//    {
//        assertNotEquals(newUser.getUserID(), "NA");
//    }

    @AfterAll
    public void closeConnection() {
        try {
            DBMethod.closeConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}