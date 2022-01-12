package com.fifteen.afterLogin;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventPageMain extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JButton a1Button;
    private JButton a2Button;
    private JButton a3Button;
    private JButton a4Button;
    private JButton a5Button;
    private JButton a6Button;
    private JButton a7Button;
    private JButton a8Button;
    private JButton a9Button;
    private JButton a10Button;
    private JButton a11Button;
    private JButton a12Button;
    private JButton a13Button;
    private JButton a14Button;
    private JButton a15Button;
    private JButton a16Button;
    private JButton a17Button;
    private JButton a18Button;
    private JButton a19Button;
    private JButton a20Button;
    private JButton a21Button;
    private JButton a22Button;
    private JButton a23Button;
    private JButton a24Button;
    private JButton a25Button;
    private JButton a26Button;
    private JButton a27Button;
    private JButton a28Button;
    private JButton a29Button;
    private JButton a30Button;
    private JButton a31Button;
    private JButton button1;
    private JButton button2;
    private JButton menuButton;


    public EventPageMain() {
        frame = new JFrame("EventPageButton");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);


        a1Button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new AddEventDialog();
            }
        });
    }

}