package com.fifteen.beforeLogin.login;

/**
 * Creating a simple Login Page Panel where the user can type in his username and password and after
 * doing so, finish with a click on the login button. The Admin checks the admin checkbox to access his admin
 * privileges. //Prototype version 1, more functionalities and designs will be added soon
 * @author Ante
 */

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class LoginPage extends JFrame {
  private JTextField username;
  private JPanel panel1;
  private JPasswordField password;
  private JButton login;
  private JButton sign_up;
  private JCheckBox admin;
  private JFrame frame;

  public LoginPage() {
    frame = new JFrame("Login Frame");
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(250, 200));
    frame.setResizable(false);

    // adding the panel
    frame.add(panel1);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
  }

}
