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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private ArrayList<EventLocal> eventMonths = new ArrayList<>();
    private ArrayList<EventLocal> eventsToday = new ArrayList<>();

    public ShowEvents(User user, int currentDay, int currentMonth, int currentYear, CalendarView calendar) {

        // Create frame
        frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setResizable(true);

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
        // Clicking event cell opens ShowEventFrame
        scpane.addMouseListener(new MouseAdapter() {
        });
        tblCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                EventLocal selectedEvent;

                JTable tblCalender = (JTable) e.getSource();
                int row = tblCalender.getSelectedRow();
                int column = tblCalender.getSelectedColumn();

                if (mdlCalendar.getValueAt(row, column) != null) {

                    selectedEvent = eventsToday.get(row);

                    new EditEvent(selectedEvent);
                }

            }
        });
    }

    private void fillTable(int month, int day, int year) {

        // Get Events
        //ArrayList<EventLocal> eventMonths = new ArrayList<>();
        //ArrayList<EventLocal> eventsToday = new ArrayList<>();

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
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(4, 3, new Insets(0, 0, 0, 0), -1, -1));
        final JLabel label1 = new JLabel();
        label1.setText("Events for this day");
        panel1.add(label1, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        backButton = new JButton();
        backButton.setText("Back");
        panel1.add(backButton, new GridConstraints(3, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addEventsButton = new JButton();
        addEventsButton.setText("Add Events");
        panel1.add(addEventsButton, new GridConstraints(3, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        dateSelectedEvent = new JLabel();
        dateSelectedEvent.setText("Current Day");
        panel1.add(dateSelectedEvent, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scpane = new JScrollPane();
        panel1.add(scpane, new GridConstraints(2, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        tblCalendar = new JTable();
        scpane.setViewportView(tblCalendar);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
