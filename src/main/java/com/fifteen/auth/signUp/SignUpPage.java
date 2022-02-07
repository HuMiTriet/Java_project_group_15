package com.fifteen.auth.signUp;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;

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
import com.fifteen.events.local.localDb;
import com.fifteen.events.sync.localDatabaseFile;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import org.apache.commons.io.FileUtils;

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
    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    frame.setPreferredSize(new Dimension(500, 700));
    frame.setResizable(false);

    // adding the panel
    frame.add(SUPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    /**
     * Add event listener for create account button. When pressed will check all
     * fields to see if they are all valid, if yes then create a new user and
     * then checks if any local database file already exists, if yes then delete it
     * and create a new file, which will be uploaed to the remote database. This
     * is done to clear out older user previous data.
     * 
     * @author Ante Maric 1273904 (GUI), Triet Huynh (functionality)
     * 
     */
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

          FileUtils.deleteQuietly(new File("local.db"));

          UserDao userHandler = new UserDaoImp();

          User signUpUser = userHandler.createUserFromSignUp(enteredEmail, enteredUsername,
              enteredSecondPassword, 0);

          localDb.initializeLocalDatabase();

          try {
            localDatabaseFile.uploadLocalDatabase(signUpUser);
          } catch (IOException | SQLException e1) {
            e1.printStackTrace();
          }

          DBMethod.closeConnection();

          new CalendarView(signUpUser);
          frame.dispose();

        } else {
          return;
        }
      }

      /**
       * close connection and switch back to the login page
       */
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

  {
    // GUI initializer generated by IntelliJ IDEA GUI Designer
    // >>> IMPORTANT!! <<<
    // DO NOT EDIT OR ADD ANY CODE HERE!
    $$$setupUI$$$();
  }

  /**
   * Method generated by IntelliJ IDEA GUI Designer
   * >>> IMPORTANT!! <<<
   * DO NOT edit this method OR call it in your code!
   *
   * @noinspection ALL
   */
  private void $$$setupUI$$$() {
    SUPanel = new JPanel();
    SUPanel.setLayout(new GridLayoutManager(15, 5, new Insets(0, 0, 0, 0), -1, -1));
    emailDisplay = new JLabel();
    emailDisplay.setText("Email adress");
    SUPanel.add(emailDisplay, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer1 = new Spacer();
    SUPanel.add(spacer1, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    firstPasswordDisplay = new JLabel();
    firstPasswordDisplay.setText("Password");
    SUPanel.add(firstPasswordDisplay,
        new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer2 = new Spacer();
    SUPanel.add(spacer2, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    reEnteredPasswordDisplay = new JLabel();
    reEnteredPasswordDisplay.setText("Re-Enter Password");
    SUPanel.add(reEnteredPasswordDisplay,
        new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer3 = new Spacer();
    SUPanel.add(spacer3, new GridConstraints(2, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
        GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    final Spacer spacer4 = new Spacer();
    SUPanel.add(spacer4, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
        GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    createAccountButton = new JButton();
    createAccountButton.setText("Create account");
    SUPanel.add(createAccountButton,
        new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer5 = new Spacer();
    SUPanel.add(spacer5, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer6 = new Spacer();
    SUPanel.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer7 = new Spacer();
    SUPanel.add(spacer7, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
        GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    reEnteredPassword = new JPasswordField();
    reEnteredPassword.setText("");
    SUPanel.add(reEnteredPassword,
        new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    password = new JPasswordField();
    SUPanel.add(password,
        new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    signInButton = new JButton();
    signInButton.setText("Sign In");
    SUPanel.add(signInButton,
        new GridConstraints(13, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer8 = new Spacer();
    SUPanel.add(spacer8, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final JLabel label1 = new JLabel();
    label1.setText("Already have an account?");
    SUPanel.add(label1, new GridConstraints(12, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    passwordMatch = new JLabel();
    passwordMatch.setText("");
    SUPanel.add(passwordMatch, new GridConstraints(11, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label2 = new JLabel();
    label2.setText("Sign Up");
    SUPanel.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    emailLabel = new JLabel();
    emailLabel.setText("");
    SUPanel.add(emailLabel, new GridConstraints(5, 2, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    usernameDisplay = new JLabel();
    usernameDisplay.setText("Username");
    SUPanel.add(usernameDisplay, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    email = new JTextField();
    email.setText("");
    SUPanel.add(email,
        new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    usernameLabel = new JLabel();
    usernameLabel.setText("");
    SUPanel.add(usernameLabel, new GridConstraints(3, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    username = new JTextField();
    username.setText("");
    SUPanel.add(username,
        new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return SUPanel;
  }
}
