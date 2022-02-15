package com.fifteen.events;

import com.fifteen.database.User;
import com.fifteen.events.local.EventLocal;
import com.fifteen.events.local.localDbMethod;
import com.fifteen.events.eventMethod.TimeMethod;
import com.fifteen.events.eventMethod.eventsPerDate;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Class displays a table of all events in a single day
 * @author Tim Görß 1252200
 */

public class ShowEvents extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JButton addEventsButton;
    private JButton backButton;
    private JLabel dateSelectedEvent;
    private JTable tableEvents;
    private DefaultTableModel modelEvents;
    private JScrollPane scpane;
    private ArrayList<EventLocal> eventMonths = new ArrayList<>();
    private ArrayList<EventLocal> eventsToday = new ArrayList<>();

    public ShowEvents(User user, int currentDay, int currentMonth, int currentYear, CalendarView calendar) {

        // Create frame @Tim Görß 1252200
        frame = new JFrame();
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1000, 700));
        frame.setResizable(true);

       // Add panel to frame @Tim Görß 1252200
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Create TableModel and add it to Table @Tim Görß 1252200
        modelEvents = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableEvents.setModel(modelEvents);
        scpane.add(tableEvents);
        scpane.setViewportView(tableEvents);

        // Add columns with even properties @Tim Görß 1252200
        String[] weekdays = {"Title", "Description", "Start", "Duration", "Location", "Participants", "Priority"};
        for (int i = 0; i < 7; i++) {
            modelEvents.addColumn(weekdays[i]);
        }
        modelEvents.setRowCount(10);
        tableEvents.setRowHeight(50);

        // Set current day display  @Tim Görß 1252200
        String day = String.valueOf(currentDay);
        String month = String.valueOf(currentMonth + 1);
        String year = String.valueOf(currentYear);
        dateSelectedEvent.setText(day + " " + month + " " + year);

        // Close Window and get back to Calendar  @Tim Görß 1252200
        backButton.addActionListener(e -> frame.dispose());

        // Opens AddEvents page  @Tim Görß 1252200
        addEventsButton.addActionListener(e -> {
            new AddEvents(user, currentDay, currentMonth, currentYear, calendar);
            frame.dispose();
        });

        fillTable(currentMonth, currentDay, currentYear);

        // Clicking event opens EditEvent frame with detailed information bout selected event  @Tim Görß 1252200
        scpane.addMouseListener(new MouseAdapter() {
        });
        tableEvents.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                EventLocal selectedEvent;

                JTable tblCalender = (JTable) e.getSource();
                int row = tblCalender.getSelectedRow();
                int column = tblCalender.getSelectedColumn();

                if (modelEvents.getValueAt(row, column) != null) {

                    selectedEvent = eventsToday.get(row);

                    new EditEvent(selectedEvent, user, calendar, ShowEvents.this, currentDay, currentMonth, currentYear);
                }

            }
        });
    }
    /**
     * Function to fill the table with data of the events
     * @author Tim Görß 1252200
     */
    public void fillTable(int month, int day, int year) {

        // Get events from database @Tim Görß 1252200
        try {
            eventMonths = localDbMethod.buildEventLocal(month, year);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!eventMonths.isEmpty()) {
            eventsToday = eventsPerDate.getEventOfDate(eventMonths, day);
        }

        // Clear table @Tim Görß 1252200
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 7; j++) {
                modelEvents.setValueAt(null, i, j);
            }
        }

        // Add events to table @Tim Görß 1252200
        if (!eventsToday.isEmpty()) {
            for (int i = 0; i < eventsToday.size(); i++) {
                int j = 0;

                modelEvents.setValueAt(eventsToday.get(i).getEventName(), i, j);
                modelEvents.setValueAt(eventsToday.get(i).getEventDescription(), i, j + 1);
                modelEvents.setValueAt(TimeMethod.getCorrectTimeFormat(eventsToday.get(i).getDayOfEvent()), i, j + 2);
                modelEvents.setValueAt(eventsToday.get(i).getEvent_duration_minute() + " " + "Minutes", i, j + 3);
                modelEvents.setValueAt(eventsToday.get(i).getLocation().getName(), i, j + 4);
                modelEvents.setValueAt(eventsToday.get(i).getParticipants_email(), i, j + 5);
                modelEvents.setValueAt(eventsToday.get(i).getPriority(), i, j + 6);

                // Change the priority colour
                tableEvents.setDefaultRenderer(Object.class, new tableEventsRenderer());
            }
        }

    }
    /**
     * Renderer used to change the colour of the cells
     * in the column priority
     * @author Tim Görß 1252200
     */
    public class tableEventsRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            //Default white background @Tim Görß
            setBackground(new Color(255, 255, 255));

            // Changes color of cells based on priority
            if (value != null) {
                if (column == 6) {
                    if (Objects.equals(value.toString(), "low")) {
                        setBackground(new Color(119, 206, 13));

                    } else if (Objects.equals(value.toString(), "medium")) {
                        setBackground(new Color(208, 196, 65));

                    } else if (Objects.equals(value.toString(), "high")) {
                        setBackground(new Color(227, 26, 26));
                    }
                }
            }
            setForeground(Color.black);
            return this;
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
        tableEvents = new JTable();
        scpane.setViewportView(tableEvents);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
