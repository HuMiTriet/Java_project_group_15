package com.fifteen.events;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.border.TitledBorder;

import com.fifteen.database.User;
import com.fifteen.events.ShowEvents;
import com.fifteen.events.eventMethod.TimeMethod;
import com.fifteen.events.local.CheckDate;
import com.fifteen.events.local.EventLocal;
import com.fifteen.events.local.Location;
import com.fifteen.events.local.localDbMethod;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.toedter.calendar.JDateChooser;

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
  private JTextField reminderText;
  private JLabel reminderLabel;
  private JList ParticipantList;
  private GregorianCalendar chosenGregorianCalendar;

  public AddEvents(User user, int day, int month, int year, CalendarView calendar) {

    frame = new JFrame("Add EventLocal");

    Vector<String> contacts;

    try {
      contacts = localDbMethod.getAllContacts();
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(contacts);
      ParticipantList.setModel(model);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    frame.setPreferredSize(new Dimension(500, 700));
    frame.setResizable(false);

    frame.add(panel1);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    //Preset JDatePicker to current or selected day
    Date today = new Date(year - 1900, month, day);
    jDateChooser.setDate(today);


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
          int reminderMinute = TimeMethod.reminderMinutes(reminderTime);

          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

          String date = sdf.format(jDateChooser.getDate());
          String timeDate = startTimeString + " " + date;

          GregorianCalendar currentDay = CheckDate.validateTimeDate(timeDate);
          Set<String> participants = new HashSet<String>(ParticipantList.getSelectedValuesList());

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
          calendar.updateCalendar(month, year);
          new ShowEvents(user, day, month, year, calendar);
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

}
