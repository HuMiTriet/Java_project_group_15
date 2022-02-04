package com.fifteen.profile;

import com.fifteen.events.AddEvents;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//@author Jorge

public class ProfilePage extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JLabel username;
    private JPanel conTitle;
    private JButton addContact;
    private JButton delContact;


    public ProfilePage() {
        frame = new JFrame("Profile");

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 450));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        addContact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddContact();
            }
        });
    }


}
