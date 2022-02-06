package com.fifteen.events;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import com.fifteen.database.User;
import com.fifteen.events.eventMethod.TimeMethod;
import com.fifteen.events.local.CheckDate;
import com.fifteen.events.local.EventLocal;
import com.fifteen.events.local.Location;
import com.fifteen.events.local.localDbMethod;
import com.fifteen.events.reminder.convertOptionToMinute;
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
  private JTextField locationText;
  private JTextField LongitdudeText;
  private JTextField LatitudeText;
  private JTextField reminderText;
  private JList ParticipantList;
  private JComboBox reminderComboBox;
  private JLabel locationLabel;
  private JLabel longtitudeLabel;
  private JLabel latitudeLabel;
  private GregorianCalendar chosenGregorianCalendar;

  public AddEvents(User user, int day, int month, int year, CalendarView calendar) {

    frame = new JFrame("Add EventLocal");

    Vector<String> contacts;

    $$$setupUI$$$();
    try {
      contacts = localDbMethod.getAllContacts();
      DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>(contacts);
      ParticipantList.setModel(model);
    } catch (SQLException e1) {
      e1.printStackTrace();
    }

    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    frame.setPreferredSize(new Dimension(500, 700));
    frame.setResizable(true);

    frame.add(panel1);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    // Preset JDatePicker to current or selected day
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

        if (locationText.getText().isBlank()) {
          locationLabel.setText("Please enter a location");
          allFieldsCorrect = false;
        } else
          locationLabel.setText("");

        if (allFieldsCorrect) {
          Location location = new Location(locationText.getText(), Double.parseDouble(LongitdudeText.getText()),
              Double.parseDouble(LatitudeText.getText()));

          long durationMinute = TimeMethod.minutesBetween(startTime, endTime);

          SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

          String date = sdf.format(jDateChooser.getDate());
          String timeDate = startTimeString + " " + date;

          GregorianCalendar currentDay = CheckDate.validateTimeDate(timeDate);
          Set<String> participants = new HashSet<String>(ParticipantList.getSelectedValuesList());

          String reminder = reminderComboBox.getSelectedItem().toString();

          int reminderMinute = convertOptionToMinute.convert(reminder);

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
    panel1.setLayout(new GridLayoutManager(31, 5, new Insets(0, 0, 0, 0), -1, -1));
    panel1.setBorder(BorderFactory.createTitledBorder(null, "g", TitledBorder.DEFAULT_JUSTIFICATION,
        TitledBorder.DEFAULT_POSITION, null, null));
    final JLabel label1 = new JLabel();
    label1.setText("Event Name");
    panel1.add(label1, new GridConstraints(0, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer1 = new Spacer();
    panel1.add(spacer1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
        GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    final Spacer spacer2 = new Spacer();
    panel1.add(spacer2, new GridConstraints(0, 4, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
        GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    eventNameText = new JTextField();
    eventNameText.setText("");
    panel1.add(eventNameText,
        new GridConstraints(1, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    final JLabel label2 = new JLabel();
    label2.setText("Event description");
    panel1.add(label2, new GridConstraints(3, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label3 = new JLabel();
    label3.setText("Start time");
    panel1.add(label3, new GridConstraints(5, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    startTimeText = new JTextField();
    startTimeText.setText("");
    panel1.add(startTimeText,
        new GridConstraints(6, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    final JLabel label4 = new JLabel();
    label4.setText("End time");
    panel1.add(label4, new GridConstraints(8, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    eventDurationText = new JTextField();
    eventDurationText.setText("");
    panel1.add(eventDurationText,
        new GridConstraints(9, 1, 1, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    eventDescriptionText = new JTextArea();
    panel1.add(eventDescriptionText,
        new GridConstraints(4, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50),
            null, 0, false));
    final JLabel label5 = new JLabel();
    label5.setText("Date");
    panel1.add(label5, new GridConstraints(11, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label6 = new JLabel();
    label6.setText("Priority");
    panel1.add(label6, new GridConstraints(14, 1, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label7 = new JLabel();
    label7.setText("Participants");
    panel1.add(label7, new GridConstraints(16, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label8 = new JLabel();
    label8.setText("Reminder");
    panel1.add(label8, new GridConstraints(18, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer3 = new Spacer();
    panel1.add(spacer3, new GridConstraints(29, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL,
        1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    addEventButton = new JButton();
    addEventButton.setText("Add Event");
    panel1.add(addEventButton,
        new GridConstraints(30, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    cancelButton = new JButton();
    cancelButton.setText("Cancel");
    panel1.add(cancelButton,
        new GridConstraints(30, 3, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    priorityPicker = new JComboBox();
    final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
    defaultComboBoxModel1.addElement("high");
    defaultComboBoxModel1.addElement("medium");
    defaultComboBoxModel1.addElement("low");
    priorityPicker.setModel(defaultComboBoxModel1);
    panel1.add(priorityPicker,
        new GridConstraints(15, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    panel1.add(jDateChooser,
        new GridConstraints(12, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    Location = new JLabel();
    Location.setText("Location");
    panel1.add(Location, new GridConstraints(20, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    locationText = new JTextField();
    locationText.setText("");
    panel1.add(locationText,
        new GridConstraints(21, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    final JLabel label9 = new JLabel();
    label9.setText("Longitude");
    panel1.add(label9, new GridConstraints(23, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    LongitdudeText = new JTextField();
    LongitdudeText.setText("");
    panel1.add(LongitdudeText,
        new GridConstraints(24, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    final JLabel label10 = new JLabel();
    label10.setText("Latitude");
    panel1.add(label10, new GridConstraints(26, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    LatitudeText = new JTextField();
    LatitudeText.setText("");
    panel1.add(LatitudeText,
        new GridConstraints(27, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null,
            0, false));
    final JScrollPane scrollPane1 = new JScrollPane();
    panel1.add(scrollPane1,
        new GridConstraints(17, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW,
            GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    ParticipantList = new JList();
    scrollPane1.setViewportView(ParticipantList);
    reminderComboBox = new JComboBox();
    final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
    defaultComboBoxModel2.addElement("10 minutes");
    defaultComboBoxModel2.addElement("1 hour");
    defaultComboBoxModel2.addElement("3 days");
    defaultComboBoxModel2.addElement("1 week");
    reminderComboBox.setModel(defaultComboBoxModel2);
    panel1.add(reminderComboBox,
        new GridConstraints(19, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL,
            GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    locationLabel = new JLabel();
    locationLabel.setText("");
    panel1.add(locationLabel, new GridConstraints(22, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    errorGregorianCalendar = new JLabel();
    errorGregorianCalendar.setText("");
    panel1.add(errorGregorianCalendar,
        new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    eventDurationLabel = new JLabel();
    eventDurationLabel.setText("");
    panel1.add(eventDurationLabel,
        new GridConstraints(10, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
            GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    startTimeLabel = new JLabel();
    startTimeLabel.setText("");
    panel1.add(startTimeLabel, new GridConstraints(7, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    eventNameLabel = new JLabel();
    eventNameLabel.setText("");
    panel1.add(eventNameLabel, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    longtitudeLabel = new JLabel();
    longtitudeLabel.setText("");
    panel1.add(longtitudeLabel, new GridConstraints(25, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    latitudeLabel = new JLabel();
    latitudeLabel.setText("");
    panel1.add(latitudeLabel, new GridConstraints(28, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE,
        GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return panel1;
  }

}
