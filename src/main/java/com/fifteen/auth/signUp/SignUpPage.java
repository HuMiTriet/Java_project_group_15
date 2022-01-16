package com.fifteen.auth.signUp;

import com.fifteen.auth.login.LoginPage;
import com.fifteen.auth.security.RegexChecker;
import com.fifteen.database.DBMethod;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.EventPageMain;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * Sign Up page with email regex, check if username and email existed. If all
 * passwed a new user will be added to the database
 * 
 * @author Ante(intially), PJ(modify)
 */
public class SignUpPage extends JFrame {
  private JTextField username;
  private JTextField FirstNameTextField;
  private JLabel userNameLabel;
  private JLabel FirstNameLabel;
  private JTextField LastNameTextField;
  private JLabel LastNameLabel;
  private JTextField email;
  private JLabel EmailAdressLabel;
  private JLabel PasswordLabel;
  private JLabel ReEnterPasswordLabel;
  private JButton createAccountButton;
  private JButton signInButton;
  private JPanel SUPanel;
  private JPasswordField reEnteredPassword;
  private JPasswordField password;
  private JLabel usernameLabel;
  private JLabel emailLabel;
  private JLabel passwordMatch;
  private JFrame frame;

  public SignUpPage() {
    frame = new JFrame("SignUp Frame");
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
        DBMethod.createConnection();
        String enteredEmail = email.getText();
        String enteredUsername = username.getText();
        // String enteredPassword = new String(password.getPassword());

        try {
          if (DBMethod.checkfieldExisted(enteredUsername, 'u')) {
            userNameLabel.setText("Username has been taken");
            return;
          } else {
            userNameLabel.setText("");
          }

          if (RegexChecker.checkEmail(enteredEmail)) {
            emailLabel.setText("");
          } else {
            emailLabel.setText("Invalid email format");
            return;
          }

          if (DBMethod.checkfieldExisted(enteredEmail, 'e')) {
            emailLabel.setText("Account with this email already existed");
            return;
          } else {
            emailLabel.setText("");
          }

        } catch (SQLException ex) {
          JOptionPane.showMessageDialog(null, "Connecting to database failed"
              + " Please check your internet connection");
          DBMethod.closeConnection();
          ex.printStackTrace();
          return;
        }

        String firstPassword = new String(password.getPassword());
        String secondPassword = new String(reEnteredPassword.getPassword());

        if (firstPassword.equals(secondPassword)) {
          passwordMatch.setText("");
        } else {
          passwordMatch.setText("Passwords do not match");
          return;
        }

        UserDao userHandler = new UserDaoImp();

        User newUser = userHandler.createUserFromSignUp(enteredEmail, enteredUsername, firstPassword, 0);

        new EventPageMain();
        frame.dispose();
        DBMethod.closeConnection();
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
    SUPanel.setLayout(new GridLayoutManager(23, 5, new Insets(0, 0, 0, 0), -1, -1));
    final Spacer spacer1 = new Spacer();
    SUPanel.add(spacer1, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer2 = new Spacer();
    SUPanel.add(spacer2, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    userNameLabel = new JLabel();
    userNameLabel.setText("Username");
    SUPanel.add(userNameLabel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    username = new JTextField();
    SUPanel.add(username,
        new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    FirstNameLabel = new JLabel();
    FirstNameLabel.setText("First name");
    SUPanel.add(FirstNameLabel, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer3 = new Spacer();
    SUPanel.add(spacer3, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    FirstNameTextField = new JTextField();
    SUPanel.add(FirstNameTextField,
        new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    LastNameLabel = new JLabel();
    LastNameLabel.setText("Last name");
    SUPanel.add(LastNameLabel, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    LastNameTextField = new JTextField();
    SUPanel.add(LastNameTextField,
        new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    final Spacer spacer4 = new Spacer();
    SUPanel.add(spacer4, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    EmailAdressLabel = new JLabel();
    EmailAdressLabel.setText("Email adress");
    SUPanel.add(EmailAdressLabel,
        new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer5 = new Spacer();
    SUPanel.add(spacer5, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    PasswordLabel = new JLabel();
    PasswordLabel.setText("Password");
    SUPanel.add(PasswordLabel, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    email = new JTextField();
    SUPanel.add(email,
        new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    final Spacer spacer6 = new Spacer();
    SUPanel.add(spacer6, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    ReEnterPasswordLabel = new JLabel();
    ReEnterPasswordLabel.setText("Re-Enter Password");
    SUPanel.add(ReEnterPasswordLabel,
        new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer7 = new Spacer();
    SUPanel.add(spacer7, new GridConstraints(4, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
        GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    final Spacer spacer8 = new Spacer();
    SUPanel.add(spacer8, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
        GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    createAccountButton = new JButton();
    createAccountButton.setText("Create account");
    SUPanel.add(createAccountButton,
        new GridConstraints(21, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer9 = new Spacer();
    SUPanel.add(spacer9, new GridConstraints(22, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer10 = new Spacer();
    SUPanel.add(spacer10, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer11 = new Spacer();
    SUPanel.add(spacer11, new GridConstraints(21, 2, 1, 1, GridConstraints.ANCHOR_CENTER,
        GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    reEnteredPassword = new JPasswordField();
    SUPanel.add(reEnteredPassword,
        new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    password = new JPasswordField();
    SUPanel.add(password,
        new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    signInButton = new JButton();
    signInButton.setText("Sign In");
    SUPanel.add(signInButton,
        new GridConstraints(21, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer12 = new Spacer();
    SUPanel.add(spacer12, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final JLabel label1 = new JLabel();
    label1.setText("Already have an account?");
    SUPanel.add(label1, new GridConstraints(20, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label2 = new JLabel();
    label2.setText("Sign Up");
    SUPanel.add(label2, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    usernameLabel = new JLabel();
    usernameLabel.setText("");
    SUPanel.add(usernameLabel, new GridConstraints(4, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    emailLabel = new JLabel();
    emailLabel.setText("");
    SUPanel.add(emailLabel, new GridConstraints(13, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    passwordMatch = new JLabel();
    passwordMatch.setText("");
    SUPanel.add(passwordMatch, new GridConstraints(19, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return SUPanel;
  }

}
