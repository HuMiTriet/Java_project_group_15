package com.fifteen.events;

import com.fifteen.database.User;
import com.fifteen.events.eventMethod.TimeMethod;
import com.fifteen.events.local.CheckDate;
import com.fifteen.events.local.EventLocal;
import com.fifteen.events.local.Location;
import com.fifteen.events.local.localDbMethod;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

public class AddEvents extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JTextField eventNameText;
    private JTextField startTimeText;
    private JTextField eventDurationText;
    private JTextArea eventDescriptionText;
    private JButton addEventButton;
    private JButton cancelButton;
    private JComboBox priorityPicker;
    private JDateChooser jDateChooser;
    private JLabel errorGregorianCalendar;
    private JLabel eventNameLabel;
    private JLabel startTimeLabel;
    private JLabel eventDurationLabel;
    private JLabel Location;
    private JTextField LocationText;
    private JTextField LongitdudeText;
    private JTextField LatitudeText;
    private JComboBox participantsComboBox;
    private JTextField reminderText;
    private JLabel reminderLabel;
    private GregorianCalendar chosenGregorianCalendar;

    public AddEvents(User user) {

        frame = new JFrame("Add EventLocal");
        $$$setupUI$$$();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 700));
        frame.setResizable(false);

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        addEventButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // frame.dispose();

                chosenGregorianCalendar = (GregorianCalendar) jDateChooser.getCalendar();
                boolean allFieldsCorrect = true;

                if (chosenGregorianCalendar == null) {
                    errorGregorianCalendar.setText("Please enter a correct date format");
                    allFieldsCorrect = false;
                } else
                    errorGregorianCalendar.setText("");

                if (eventNameText.getText().isBlank()) {
                    eventNameLabel.setText("Please enter an event name");
                    allFieldsCorrect = false;
                } else
                    eventNameLabel.setText("");

                String startTimeString = startTimeText.getText();
                GregorianCalendar startTime = CheckDate.validateTime(startTimeString);
                if (startTimeString.isBlank() == true || startTime == null) {
                    startTimeLabel.setText("Please enter a valid start time(format HH:mm)");
                    allFieldsCorrect = false;
                } else
                    startTimeLabel.setText("");

                String reminderString = reminderText.getText();
                GregorianCalendar reminderTime = CheckDate.validateTime(reminderString);
                if (reminderString.isBlank() == true || reminderTime == null) {
                    reminderLabel.setText("Please enter a valid reminder(format HH:mm)");
                    allFieldsCorrect = false;
                } else
                    reminderLabel.setText("");

                String endTimeString = eventDurationText.getText();
                GregorianCalendar endTime = CheckDate.validateTime(endTimeString);
                if (endTimeString.isBlank() == true || endTime == null) {
                    eventDurationLabel.setText("Please enter a valid end time(format HH:mm)");
                    allFieldsCorrect = false;
                } else {
                    eventDurationLabel.setText("");
                    if (startTime.compareTo(endTime) > 0) {
                        eventDurationLabel.setText("The end time should be after the start time");
                        allFieldsCorrect = false;
                    }
                }

                if (allFieldsCorrect) {

                    Location location = new Location(LocationText.getText(), Double.parseDouble(LongitdudeText.getText()),
                            Double.parseDouble(LatitudeText.getText()));

                    long durationMinute = TimeMethod.minutesBetween(startTime, endTime);
                    int reminderMinute = (int) TimeMethod.minutesBetween(startTime, reminderTime);

                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

                    String date = sdf.format(jDateChooser.getDate());
                    String timeDate = startTimeString + " " + date;

                    GregorianCalendar currentDay = CheckDate.validateTimeDate(timeDate);
                    Set<String> participants = new HashSet<String>();
                    participants.add("Year");
                    participants.add("of");
                    participants.add("the");
                    participants.add("tiger");

                    EventLocal eventLocal = new EventLocal(
                            eventNameText.getText(),
                            eventDescriptionText.getText(),
                            currentDay,
                            durationMinute,
                            reminderMinute,
                            participants, // fixed participants
                            location, // manually typed in location
                            priorityPicker.getSelectedItem().toString());

                    localDbMethod.addEventLocal(eventLocal);

                    frame.dispose();
                }
            }

        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
            }
        });
    }

    private void createUIComponents() {
        jDateChooser = new JDateChooser();
        jDateChooser.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        jDateChooser.setDateFormatString("dd/MM/yyyy");
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        createUIComponents();
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(24, 5, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBorder(BorderFactory.createTitledBorder(null, "g", TitledBorder.DEFAULT_JUSTIFICATION, TitledBorder.DEFAULT_POSITION, null, null));
        final JLabel label1 = new JLabel();
        label1.setText("Event Name");
        panel1.add(label1, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        eventNameText = new JTextField();
        eventNameText.setText("");
        panel1.add(eventNameText, new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setText("Event description");
        panel1.add(label2, new GridConstraints(2, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setText("Start time");
        panel1.add(label3, new GridConstraints(4, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startTimeText = new JTextField();
        startTimeText.setText("");
        panel1.add(startTimeText, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setText("End time");
        panel1.add(label4, new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eventDurationText = new JTextField();
        eventDurationText.setText("");
        panel1.add(eventDurationText, new GridConstraints(7, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        eventDescriptionText = new JTextArea();
        panel1.add(eventDescriptionText, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setText("Date");
        panel1.add(label5, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setText("Priority");
        panel1.add(label6, new GridConstraints(10, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setText("Participants");
        panel1.add(label7, new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Reminder");
        panel1.add(label8, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(22, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        addEventButton = new JButton();
        addEventButton.setText("Add Event");
        panel1.add(addEventButton, new GridConstraints(23, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        cancelButton = new JButton();
        cancelButton.setText("Cancel");
        panel1.add(cancelButton, new GridConstraints(23, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        priorityPicker = new JComboBox();
        final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
        defaultComboBoxModel1.addElement("high");
        defaultComboBoxModel1.addElement("medium");
        defaultComboBoxModel1.addElement("low");
        priorityPicker.setModel(defaultComboBoxModel1);
        panel1.add(priorityPicker, new GridConstraints(11, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        panel1.add(jDateChooser, new GridConstraints(9, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        errorGregorianCalendar = new JLabel();
        errorGregorianCalendar.setText("");
        panel1.add(errorGregorianCalendar, new GridConstraints(9, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eventNameLabel = new JLabel();
        eventNameLabel.setText("");
        panel1.add(eventNameLabel, new GridConstraints(1, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        startTimeLabel = new JLabel();
        startTimeLabel.setText("");
        panel1.add(startTimeLabel, new GridConstraints(5, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        eventDurationLabel = new JLabel();
        eventDurationLabel.setText("");
        panel1.add(eventDurationLabel, new GridConstraints(7, 4, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        Location = new JLabel();
        Location.setText("Location");
        panel1.add(Location, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LocationText = new JTextField();
        LocationText.setText("");
        panel1.add(LocationText, new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Longitude");
        panel1.add(label9, new GridConstraints(18, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LongitdudeText = new JTextField();
        LongitdudeText.setText("");
        panel1.add(LongitdudeText, new GridConstraints(19, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setText("Latitude");
        panel1.add(label10, new GridConstraints(20, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        LatitudeText = new JTextField();
        LatitudeText.setText("");
        panel1.add(LatitudeText, new GridConstraints(21, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        participantsComboBox = new JComboBox();
        panel1.add(participantsComboBox, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        reminderText = new JTextField();
        reminderText.setText("");
        panel1.add(reminderText, new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        reminderLabel = new JLabel();
        reminderLabel.setText("");
        panel1.add(reminderLabel, new GridConstraints(15, 3, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
