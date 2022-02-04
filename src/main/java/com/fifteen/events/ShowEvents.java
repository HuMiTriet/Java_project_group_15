package com.fifteen.events;

import com.fifteen.database.User;
import com.fifteen.events.local.EventLocal;
import com.fifteen.events.local.localDbMethod;
import com.fifteen.events.eventMethod.eventsPerDate;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

/**
 * @author Tim
 */

public class ShowEvents extends JFrame {
  private JFrame frame;
  private JPanel panel1;
  private JButton addEventsButton;
  private JButton backButton;
  private JLabel dateSelectedEvent;
  private JTable tblCalendar;
  private DefaultTableModel mdlCalendar;
  private JScrollPane scpane;

  public ShowEvents(User user, int currentDay, int currentMonth, int currentYear, CalendarView calendar) {

    // Create frame
    frame = new JFrame();
    frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
    frame.setPreferredSize(new Dimension(1000, 700));
    frame.setResizable(false);

    frame.add(panel1);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);

    // Create TableModel and add it to Table
    mdlCalendar = new DefaultTableModel() {
      public boolean isCellEditable(int row, int column) {
        return false;
      }
    };
    tblCalendar.setModel(mdlCalendar);
    scpane.add(tblCalendar);
    scpane.setViewportView(tblCalendar);

    // Add columns with even properties
    String[] weekdays = {"Title", "Description", "Start", "Duration", "Location", "Participants", "Priority"};
    for (int i = 0; i < 7; i++) {
      mdlCalendar.addColumn(weekdays[i]);
    }

    // Set Table parameters
    tblCalendar.setRowHeight(50);
    mdlCalendar.setColumnCount(7);
    mdlCalendar.setRowCount(10);

    // Set current day display
    String day = String.valueOf(currentDay);
    String month = String.valueOf(currentMonth + 1);
    String year = String.valueOf(currentYear);
    dateSelectedEvent.setText(day + " " + month + " " + year);

    // Close Window and get back to Calendar
    backButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        frame.dispose();
      }
    });

    // Opens AddEvents page
    addEventsButton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
        new AddEvents(user, currentDay, currentMonth, currentYear, calendar);
        frame.dispose();
      }
    });
    fillTable(currentMonth, currentDay, currentYear);
  }

  private void fillTable(int month, int day, int year) {

    // Get Events
    ArrayList<EventLocal> eventMonths = new ArrayList<>();
    ArrayList<EventLocal> eventsToday = new ArrayList<>();

    try {
      eventMonths = localDbMethod.buildEventLocal(month, year);
    } catch (SQLException e) {
      e.printStackTrace();
    }

    if (eventMonths.isEmpty() != true) {
      eventsToday = eventsPerDate.getEventOfDate(eventMonths, day);
    }

    // Clear table
    for (int i = 0; i < 10; i++) {
      for (int j = 0; j < 7; j++) {
        mdlCalendar.setValueAt(null, i, j);
      }
    }

    // Add events to table
    if (eventsToday.isEmpty() != true) {
      for (int i = 0; i < eventsToday.size(); i++) {

        int j = 0;

        mdlCalendar.setValueAt(eventsToday.get(i).getEventName(), i, j);
        mdlCalendar.setValueAt(eventsToday.get(i).getEventDescription(), i, j + 1);
        mdlCalendar.setValueAt(eventsToday.get(i).getDayOfEvent().get(GregorianCalendar.HOUR_OF_DAY), i, j + 2);
        mdlCalendar.setValueAt(eventsToday.get(i).getEvent_duration_minute(), i, j + 3);
        mdlCalendar.setValueAt(eventsToday.get(i).getLocation().getName(), i, j + 4);
        mdlCalendar.setValueAt(null, i, j + 5); // Missing participants
        mdlCalendar.setValueAt(eventsToday.get(i).getPriority(), i, j + 6);
      }
    }

  }

}
