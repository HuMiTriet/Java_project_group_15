package com.fifteen.auth.login;

import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.fifteen.auth.admin.AdminPage;
import com.fifteen.auth.security.PasswordHasher;
import com.fifteen.auth.security.UserAuthenticator;
import com.fifteen.auth.signUp.SignUpPage;
import com.fifteen.database.DBMethod;
import com.fifteen.database.User;
import com.fifteen.database.UserDao;
import com.fifteen.database.UserDaoImp;
import com.fifteen.events.CalendarView;

/**
 * Added in email pattern checking to see if email is in correct format (the format is must have @
 * sign, after dot must have at least 2 letters), also check if email entered exists on the 
 * database. If the entered email existed and the password is correct then switch to the main 
 * event page.
 * The testing account: email: t@g.com | password: B
 * @author Ante Maric 1273904, Triet Huynh
 */

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

public class LoginPage extends JFrame {
  private JTextField email;
  private JPanel loginPanel;
  private JPasswordField password;
  private JButton login;
  private JButton signUp;
  private JLabel emailLabel;
  private JLabel passwordLabel;
  private JCheckBox showPassword;
  private JButton offlineModeButton;
  private JFrame frame;

  public LoginPage() {
    frame = new JFrame("Login Frame");
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(300, 250));
    frame.setResizable(false);

    // adding the panel
    frame.add(loginPanel);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    login.addActionListener(e -> {
      String enteredEmail = email.getText();
      String enteredPassword = new String(password.getPassword());
      boolean allFieldsCorrect = false;

      DBMethod.createConnection();

      UserAuthenticator.checkFieldEmpty(passwordLabel, enteredPassword, "Please enter your password");

      allFieldsCorrect = UserAuthenticator.checkEmailFormat(emailLabel,
              enteredEmail);

      if (allFieldsCorrect) {
        allFieldsCorrect = UserAuthenticator.authenticateEmailField(emailLabel,
                enteredEmail, "");
      } else
        return;

      allFieldsCorrect = UserAuthenticator.checkFieldEmpty(passwordLabel, enteredPassword,
              "Please enter your password");

      if (allFieldsCorrect) {
        allFieldsCorrect = UserAuthenticator.authenticatePasswordField(
                passwordLabel, enteredEmail, enteredPassword);
      } else
        return;

      if (allFieldsCorrect == true) {
        UserDao userHandler = new UserDaoImp();
        User loginUser = userHandler.createUserFromLogin(enteredEmail);

        String hashedPassword = PasswordHasher.sha2(enteredPassword);
        try {
          DBMethod.fillInUserInfoFromUserEmail(loginUser, hashedPassword);
          if (loginUser.getIsAdmin() == 0) {
            new CalendarView(loginUser);
          } else {
            new AdminPage();
          }
        } catch (SQLException e1) {
          e1.printStackTrace();
        }
        frame.dispose();
        DBMethod.closeConnection();
      }
    });

    showPassword.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        if (showPassword.isSelected()) {
          password.setEchoChar((char) 0);
        } else {
          // unicode character bullet
          password.setEchoChar('\u2022');
        }
      }
    });

    signUp.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        DBMethod.closeConnection();
        new SignUpPage();
        frame.dispose();
      }
    });
  }

}
