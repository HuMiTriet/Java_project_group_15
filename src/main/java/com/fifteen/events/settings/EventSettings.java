package com.fifteen.events.settings;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventSettings extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JButton cPW;
    private JButton cUsername;
    private JButton cEmail;

    //Settings Page @author JorgeV
    public EventSettings() {
        frame = new JFrame("Settings");

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 250));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        cUsername.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cUsername();
                frame.dispose();

            }
        });

        cEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cEmail();
                frame.dispose();
            }
        });

        cPW.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new cPassword();
                frame.dispose();
            }
        });

    }

}
