package com.twenty.one.beforeLogin.signUp;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class SignUpPage extends JFrame {
    private JTextField UserNameTextField;
    private JTextField FirstNameTextField;
    private JLabel userNameLabel;
    private JLabel FirstNameLabel;
    private JTextField LastNameTextField;
    private JLabel LastNameLabel;
    private JTextField EmailAdressTextField;
    private JLabel EmailAdressLabel;
    private JLabel PasswordLabel;
    private JLabel ReEnterPasswordLabel;
    private JButton createAccountButton;
    private JButton signInButton;
    private JPanel SUPanel;
    private JPasswordField ReEnterPasswordTextField;
    private JPasswordField PasswordTextField;
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
    }
}