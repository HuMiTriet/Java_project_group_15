// package com.fifteen;

// import static org.junit.jupiter.api.Assertions.*;

// import com.fifteen.auth.login.UserAuthenticator;
// import com.fifteen.database.DBMethod;
// import com.fifteen.database.UserDao;
// import com.fifteen.database.UserDaoImp;

// import org.junit.jupiter.api.*;

// /**
// * Unit test for the User sign up function from User
// */
// @TestInstance(TestInstance.Lifecycle.PER_CLASS)
// public class AuthenTest {

// static private final String EMAIL = "A";
// static private final String USERNAME = "A";
// static private final int IS_ADMIN = 1;
// static private final String PASSWORD = "A";

// @BeforeAll
// public void setUpNewuser() {
// // User Id
// UserDao userHandler = new UserDaoImp();
// userHandler.createUserFromSignUp(EMAIL, USERNAME, PASSWORD, IS_ADMIN);
// }

// @Test
// void correctUser() {
// assertEquals(true, UserAuthenticator.authenticate(EMAIL, PASSWORD));
// }

// @Test
// void incorrectEmail() {
// assertEquals(false, UserAuthenticator.authenticate("SUDO", PASSWORD));
// }

// @Test
// void incorrectPassword() {
// assertEquals(false, UserAuthenticator.authenticate(EMAIL, "bruh"));
// }

// @AfterAll
// public void closeConnection() {
// DBMethod.closeConnection();
// }
// }
