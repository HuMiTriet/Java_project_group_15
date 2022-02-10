package com.fifteen.events;

import com.fifteen.events.local.CheckDate;
import com.fifteen.events.local.Location;
import com.fifteen.events.reminder.convertOptionToMinute;
import com.intellij.uiDesigner.core.GridConstraints;
import com.fifteen.events.local.EventLocal;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.toedter.calendar.JDateChooser;
import com.fifteen.events.local.localDbMethod;
import com.fifteen.events.reminder.convertMinutesToOption;
import com.fifteen.events.eventMethod.TimeMethod;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

/**
 * @author Tim
 */

public class EditEvent {
  private JPanel panel1;
  private JTextField eventNameText;
  private JTextArea eventDescriptionText;
  private JTextField startTimeText;
  private JTextField endTimeTextField;
  private JDateChooser JDateChooser;
  private JComboBox priorityPicker;
  private JList ParticipantList;
  private DefaultListModel mdlList;
  private JComboBox reminderPicker;
  private JTextField locationText;
  private JTextField longtitudeText;
  private JTextField latitudeText;
  private JButton saveButton;
  private JButton editButton;
  private JButton deleteButton;
  private JScrollPane scpane1;
  private JFrame frame;
  private GregorianCalendar chosenGregorianCalendar;

  public EditEvent(EventLocal event, CalendarView calendar, ShowEvents eventsOfDay, int day, int month, int year) {

    // Create frame
    frame = new JFrame("Edit Event");
    $$$setupUI$$$();
    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    frame.setPreferredSize(new Dimension(500, 700));
    frame.setResizable(true);

    // Add panel to frame
    frame.add(panel1);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    // Initialize JList
    mdlList = new DefaultListModel();
    ParticipantList.setModel(mdlList);

    displayEventInformation(event);

    // Enable text fields
    editButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseClicked(MouseEvent e) {
        super.mouseClicked(e);

        eventNameText.setEditable(true);
        eventDescriptionText.setEditable(true);
        startTimeText.setEditable(true);
        endTimeTextField.setEditable(true);
        JDateChooser.setEnabled(true);
        ParticipantList.setEnabled(true);
        priorityPicker.setEnabled(true);
        reminderPicker.setEnabled(true);
        locationText.setEditable(true);
        latitudeText.setEditable(true);
        longtitudeText.setEditable(true);
      }
    });

    // Delete Event
    deleteButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        String id = event.getEventID();
        try {
          localDbMethod.deleteEvent(id);
        } catch (SQLException ex) {
          ex.printStackTrace();
        }
        eventsOfDay.fillTable(month, day, year);
        calendar.updateCalendar(month, year);
        frame.dispose();
      }
    });
    saveButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {

        updateEvent(event);
        eventsOfDay.fillTable(month, day, year);
        calendar.updateCalendar(month, year);
        frame.dispose();

      }
    });
  }

  private void displayEventInformation(EventLocal event) {

    // Fill out textfields with event information
    // fields are set to not enabled
    eventNameText.setText(event.getEventName());
    eventDescriptionText.setText(event.getEventDescription());
    startTimeText.setText(TimeMethod.getCorrectTimeFormat(event.getDayOfEvent()));
    endTimeTextField.setText(TimeMethod.getEndTime(event.getDayOfEvent(), event.getEvent_duration_minute()));
    Date dayOfEvent = new Date(event.getDayOfEvent().get(GregorianCalendar.YEAR) - 1900,
            event.getDayOfEvent().get(GregorianCalendar.MONTH), event.getDayOfEvent().get(GregorianCalendar.DATE));
    JDateChooser.setDate(dayOfEvent);
    mdlList.addElement(event.getParticipants_email());
    priorityPicker.setSelectedItem(event.getPriority());
    reminderPicker.setSelectedItem(convertMinutesToOption.convert(event.getMinutesUntilReminder()));
    locationText.setText(event.getLocation().getName());
    latitudeText.setText(String.valueOf(event.getLocation().getLatitude()));
    longtitudeText.setText(String.valueOf(event.getLocation().getLongitude()));


  }
  private void updateEvent(EventLocal event) {

    chosenGregorianCalendar = (GregorianCalendar) JDateChooser.getCalendar();

    String startTimeString = startTimeText.getText();
    GregorianCalendar startTime = CheckDate.validateTime(startTimeString);
    String endTimeString = endTimeTextField.getText();
    GregorianCalendar endTime = CheckDate.validateTime(endTimeString);

    long durationMinute = TimeMethod.minutesBetween(startTime, endTime);

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    String date = sdf.format(JDateChooser.getDate());
    String timeDate = startTimeString + " " + date;

    GregorianCalendar editedDay = CheckDate.validateTimeDate(timeDate);

    String reminder = reminderPicker.getSelectedItem().toString();

    int reminderMinute = convertOptionToMinute.convert(reminder);

    Location location = new Location(locationText.getText(), Double.parseDouble(longtitudeText.getText()),
            Double.parseDouble(latitudeText.getText()));




    event.setEventName(eventNameText.getText());
    event.setEventDescription(eventDescriptionText.getText());
    event.setDayOfEvent(editedDay);
    event.setEvent_duration_minute(durationMinute);
    event.setMinutesUntilReminder(reminderMinute);
    event.setPriority(priorityPicker.getSelectedItem().toString());
    event.setLocation(location);


    try {
      localDbMethod.editEvent(event);
    } catch (SQLException e) {
      e.printStackTrace();
    }

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
    panel1.setLayout(new GridLayoutManager(25, 3, new Insets(0, 0, 0, 0), -1, -1));
    final JLabel label1 = new JLabel();
    label1.setText("Event Name");
    panel1.add(label1, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    eventNameText = new JTextField();
    eventNameText.setEditable(false);
    eventNameText.setText("");
    panel1.add(eventNameText, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final JLabel label2 = new JLabel();
    label2.setText("Event  Description");
    panel1.add(label2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    eventDescriptionText = new JTextArea();
    eventDescriptionText.setEditable(false);
    panel1.add(eventDescriptionText, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(150, 50), null, 0, false));
    final JLabel label3 = new JLabel();
    label3.setText("Start Time");
    panel1.add(label3, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    startTimeText = new JTextField();
    startTimeText.setEditable(false);
    startTimeText.setText("");
    panel1.add(startTimeText, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final JLabel label4 = new JLabel();
    label4.setText("End Time");
    panel1.add(label4, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    endTimeTextField = new JTextField();
    endTimeTextField.setEditable(false);
    endTimeTextField.setText("");
    panel1.add(endTimeTextField, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final JLabel label5 = new JLabel();
    label5.setText("Date");
    panel1.add(label5, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    panel1.add(JDateChooser, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
    final JLabel label6 = new JLabel();
    label6.setText("Priority");
    panel1.add(label6, new GridConstraints(11, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    priorityPicker = new JComboBox();
    priorityPicker.setEditable(false);
    priorityPicker.setEnabled(false);
    final DefaultComboBoxModel defaultComboBoxModel1 = new DefaultComboBoxModel();
    defaultComboBoxModel1.addElement("low");
    defaultComboBoxModel1.addElement("medium");
    defaultComboBoxModel1.addElement("high");
    priorityPicker.setModel(defaultComboBoxModel1);
    panel1.add(priorityPicker, new GridConstraints(12, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label7 = new JLabel();
    label7.setText("Participants");
    panel1.add(label7, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    scpane1 = new JScrollPane();
    panel1.add(scpane1, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    ParticipantList = new JList();
    ParticipantList.setEnabled(false);
    final DefaultListModel defaultListModel1 = new DefaultListModel();
    ParticipantList.setModel(defaultListModel1);
    scpane1.setViewportView(ParticipantList);
    final JLabel label8 = new JLabel();
    label8.setText("Reminder");
    panel1.add(label8, new GridConstraints(15, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    reminderPicker = new JComboBox();
    reminderPicker.setEnabled(false);
    final DefaultComboBoxModel defaultComboBoxModel2 = new DefaultComboBoxModel();
    defaultComboBoxModel2.addElement("10 minutes");
    defaultComboBoxModel2.addElement("1 hour");
    defaultComboBoxModel2.addElement("3 days");
    defaultComboBoxModel2.addElement("1 week");
    reminderPicker.setModel(defaultComboBoxModel2);
    panel1.add(reminderPicker, new GridConstraints(16, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final JLabel label9 = new JLabel();
    label9.setText("Location");
    panel1.add(label9, new GridConstraints(17, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    locationText = new JTextField();
    locationText.setEditable(false);
    locationText.setText("");
    panel1.add(locationText, new GridConstraints(18, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final JLabel label10 = new JLabel();
    label10.setText("Longtiude");
    panel1.add(label10, new GridConstraints(19, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    longtitudeText = new JTextField();
    longtitudeText.setEditable(false);
    longtitudeText.setText("");
    panel1.add(longtitudeText, new GridConstraints(20, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    final JLabel label11 = new JLabel();
    label11.setText("Latitude");
    panel1.add(label11, new GridConstraints(21, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    latitudeText = new JTextField();
    latitudeText.setEditable(false);
    latitudeText.setText("");
    panel1.add(latitudeText, new GridConstraints(22, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
    saveButton = new JButton();
    saveButton.setText("Save");
    panel1.add(saveButton, new GridConstraints(24, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    editButton = new JButton();
    editButton.setText("Edit");
    panel1.add(editButton, new GridConstraints(24, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    deleteButton = new JButton();
    deleteButton.setText("Delete");
    panel1.add(deleteButton, new GridConstraints(24, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    final Spacer spacer1 = new Spacer();
    panel1.add(spacer1, new GridConstraints(23, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
    final Spacer spacer2 = new Spacer();
    panel1.add(spacer2, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
  }

  /**
   * @noinspection ALL
   */
  public JComponent $$$getRootComponent$$$() {
    return panel1;
  }

  private void createUIComponents() {
    JDateChooser = new JDateChooser();
    JDateChooser.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    JDateChooser.setDateFormatString("dd/MM/yyyy");
    JDateChooser.setEnabled(false);
  }
}
