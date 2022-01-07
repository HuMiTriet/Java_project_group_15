package com.twenty.one.afterLogin;

import com.intellij.uiDesigner.core.GridLayoutManager;

import java.awt.*;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventPage extends JFrame {

    private JPanel contentPane;
    private JTextField textField;
    private JTextField textField_1;
    private JTextField textField_2;
    private JTextField textField_3;
    private JTextField textField_4;
    private JTextField textField_5;
    private JTextField textField_6;
    private JTextField textField_7;
    private JTextField textField_8;
    private JTextField textField_9;


    public EventPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 805, 537);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        final JTextArea textArea = new JTextArea();
        textArea.setBounds(10, 11, 758, 263);
        contentPane.add(textArea);

        JLabel lblNewLabel = new JLabel("Name");
        lblNewLabel.setBounds(29, 313, 46, 14);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Description");
        lblNewLabel_1.setBounds(164, 313, 46, 14);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("startTime");
        lblNewLabel_2.setBounds(282, 313, 46, 14);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("endTime");
        lblNewLabel_3.setBounds(408, 313, 46, 14);
        contentPane.add(lblNewLabel_3);

        JLabel lblNewLabel_4 = new JLabel("location");
        lblNewLabel_4.setBounds(525, 313, 46, 14);
        contentPane.add(lblNewLabel_4);

        JLabel lblNewLabel_5 = new JLabel("year");
        lblNewLabel_5.setBounds(525, 407, 46, 14);
        contentPane.add(lblNewLabel_5);

        JLabel lblNewLabel_6 = new JLabel("month");
        lblNewLabel_6.setBounds(408, 407, 46, 14);
        contentPane.add(lblNewLabel_6);

        JLabel lblNewLabel_7 = new JLabel("day");
        lblNewLabel_7.setBounds(282, 407, 46, 14);
        contentPane.add(lblNewLabel_7);

        JLabel lblNewLabel_8 = new JLabel("reminder");
        lblNewLabel_8.setBounds(164, 407, 46, 14);
        contentPane.add(lblNewLabel_8);

        JLabel lblNewLabel_9 = new JLabel("priority");
        lblNewLabel_9.setBounds(29, 407, 46, 14);
        contentPane.add(lblNewLabel_9);

        textField = new JTextField();
        textField.setBounds(10, 344, 86, 20);
        contentPane.add(textField);
        textField.setColumns(10);

        textField_1 = new JTextField();
        textField_1.setBounds(141, 344, 86, 20);
        contentPane.add(textField_1);
        textField_1.setColumns(10);

        textField_2 = new JTextField();
        textField_2.setBounds(261, 344, 86, 20);
        contentPane.add(textField_2);
        textField_2.setColumns(10);

        textField_3 = new JTextField();
        textField_3.setBounds(390, 344, 86, 20);
        contentPane.add(textField_3);
        textField_3.setColumns(10);

        textField_4 = new JTextField();
        textField_4.setBounds(500, 344, 86, 20);
        contentPane.add(textField_4);
        textField_4.setColumns(10);

        textField_5 = new JTextField();
        textField_5.setBounds(10, 432, 86, 20);
        contentPane.add(textField_5);
        textField_5.setColumns(10);

        textField_6 = new JTextField();
        textField_6.setBounds(141, 432, 86, 20);
        contentPane.add(textField_6);
        textField_6.setColumns(10);

        textField_7 = new JTextField();
        textField_7.setBounds(274, 432, 86, 20);
        contentPane.add(textField_7);
        textField_7.setColumns(10);

        textField_8 = new JTextField();
        textField_8.setBounds(390, 432, 86, 20);
        contentPane.add(textField_8);
        textField_8.setColumns(10);

        textField_9 = new JTextField();
        textField_9.setBounds(500, 432, 86, 20);
        contentPane.add(textField_9);
        textField_9.setColumns(10);

        JButton btnNewButton = new JButton("New button");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                String name = textField.getText();
                String des = textField_1.getText();
                String stringStart = textField_2.getText();
                String stringEnd = textField_3.getText();
                String location = textField_4.getText();
                String stringPriority = textField_5.getText();
                String stringReminder = textField_6.getText();
                String stringDay = textField_7.getText();
                String stringMonth = textField_8.getText();
                String stringYear = textField_9.getText();

                int startTime = Integer.parseInt(stringStart);
                int endTime = Integer.parseInt(stringEnd);
                int priority = Integer.parseInt(stringPriority);
                int reminder = Integer.parseInt(stringReminder);
                int day = Integer.parseInt(stringDay);
                int month = Integer.parseInt(stringMonth);
                int year = Integer.parseInt(stringYear);

                Events event = new Events(name, des, startTime, endTime, location, priority, reminder, day, month, year);

                textArea.append(event.getEventName() + " " + event.getDescription() + " " + stringStart + " " + stringEnd + " " + event.getLocation() + " " + stringPriority + " " + stringReminder + " " +
                        stringDay + " " + stringMonth + " " + stringYear + "\n");

            }
        });
        btnNewButton.setBounds(635, 381, 89, 23);
        contentPane.add(btnNewButton);
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
        final JPanel panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(1, 1, new Insets(0, 0, 0, 0), -1, -1));
    }
}
