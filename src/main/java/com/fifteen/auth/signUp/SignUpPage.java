package com.fifteen.auth.signUp;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.fifteen.auth.login.LoginPage;
import com.fifteen.auth.security.UserAuthenticator;
import com.fifteen.database.DBMethod;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.CalendarView;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

/**
 * Sign Up page with email regex, check if username and email existed. If all
 * passwed a new user will be added to the database
 *
 * @author Ante Maric 1273904 (GUI), Triet Huynh (functionality)
 */
public class SignUpPage extends JFrame {
  private JTextField email;
  private JLabel emailDisplay;
  private JLabel firstPasswordDisplay;
  private JLabel reEnteredPasswordDisplay;
  private JButton createAccountButton;
  private JButton signInButton;
  private JPanel SUPanel;
  private JPasswordField reEnteredPassword;
  private JPasswordField password;
  private JLabel emailLabel;
  private JLabel passwordMatch;
  private JLabel usernameDisplay;
  private JTextField username;
  private JLabel usernameLabel;
  private JFrame frame;

  public SignUpPage() {
    frame = new JFrame("Sign Up for new account");
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(500, 700));
    frame.setResizable(false);

    // adding the panel
    frame.add(SUPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    createAccountButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String enteredEmail = email.getText();
        String enteredFirstPassword = new String(password.getPassword());
        String enteredSecondPassword = new String(reEnteredPassword.getPassword());
        String enteredUsername = username.getText();
        DBMethod.createConnection();
        Boolean firstPasswordFilled = false;
        Boolean emailCorrect = false;

        // first check if all fields are emtpy
        UserAuthenticator.checkFieldEmpty(usernameLabel, enteredUsername,
            "Please enter your username");

        firstPasswordFilled = UserAuthenticator.checkFieldEmpty(passwordMatch, enteredFirstPassword,
            "Please enter your password");

        if (firstPasswordFilled)
          UserAuthenticator.checkFieldEmpty(passwordMatch, enteredSecondPassword,
              "Please re-enter your password");

        emailCorrect = UserAuthenticator.checkEmailFormat(emailLabel, enteredEmail);

        if (emailCorrect == true) {
          emailCorrect = !UserAuthenticator.authenticateEmailField(emailLabel, enteredEmail,
              "An account with this email was created");
        }

        if (!enteredFirstPassword.equals(enteredSecondPassword) && !enteredSecondPassword.isBlank()
            && !enteredFirstPassword.isBlank()) {
          passwordMatch.setText("Paswords do not match");
        }

        if (emailCorrect == false)
          return;

        if (!enteredFirstPassword.equals(enteredSecondPassword) && !enteredSecondPassword.isBlank()
            && !enteredFirstPassword.isBlank()) {
          passwordMatch.setText("Paswords do not match");
          return;
        }

        if (!enteredEmail.isBlank() && !enteredUsername.isBlank() &&
            !enteredFirstPassword.isBlank() && !enteredSecondPassword.isBlank()) {

          UserDao userHandler = new UserDaoImp();

          User signUpUser = userHandler.createUserFromSignUp(enteredEmail, enteredUsername,
              enteredSecondPassword, 0);

          DBMethod.closeConnection();

          new CalendarView(signUpUser);
          frame.dispose();

        } else {
          return;
        }
      }

    });
    signInButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new LoginPage();
        DBMethod.closeConnection();
        frame.dispose();
      }
    });
  }

}
