package com.fifteen.events.settings;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;

public class cPassword extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JTextField inNewUN;
    private JTextField enterEmailAdressTextField;
    private JTextField enterPasswordTextField;
    private JTextField enterNewPasswordTextField;
    private JButton submitButton;

    public cPassword() {
        frame = new JFrame("Password Change");

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 250));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


    }

}