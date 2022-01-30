package com.fifteen.events;

import com.fifteen.auth.login.LoginPage;
import com.fifteen.database.User;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;

/**
 * @author Tim
 */

public class CalendarView extends JFrame {

    private JTable tblCalendar;
    private JPanel panel1;
    private JScrollPane spanel1;
    private JButton nextMonth;
    private JButton prevMonth;
    private JLabel monthCalendar;
    private JLabel yearJlabel;
    private JButton addEvent;
    private DefaultTableModel mdlCalendar;
    private JFrame frame;
    private int Day, Month, Year, currentMonth, currentYear;

    private JMenuBar e_menuBar;
    private JMenu menu;
    private JMenuItem menu1, menu2, menu3, menu4;
    private JMenu sview;
    private JMenuItem sview1, sview2, sview3;
    private JMenu about;
    private JMenuItem about1;

    public CalendarView(User user) {

        // Create frame
        frame = new JFrame("Calendar View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setResizable(false);

        e_menuBar = new JMenuBar();

        // create a menu
        menu = new JMenu("Menu");
        sview = new JMenu("Switch View");
        about = new JMenu("About");

        // create menuitems
        sview1 = new JMenuItem("Daily");
        sview2 = new JMenuItem("Weekly");
        sview3 = new JMenuItem("Monthly");

        menu1 = new JMenuItem("Profile");
        menu2 = new JMenuItem("Settings");
        menu3 = new JMenuItem("Log Out");
        menu4 = new JMenuItem("Exit");

        about1 = new JMenuItem("Documentation");
        // add menu items to menu
        sview.add(sview1);
        sview.add(sview2);
        sview.add(sview3);

        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);
        menu.add(menu4);

        about.add(about1);
        // add menu to menu bar
        e_menuBar.add(menu);
        e_menuBar.add(sview);
        e_menuBar.add(about);

        // add menubar to frame
        frame.setJMenuBar(e_menuBar);

        // Add panel
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        class openSettings implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                new EventSettings();
            }
        }
        menu2.addActionListener(new openSettings());

        class logOut implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage();
                frame.dispose();
            }
        }
        menu3.addActionListener(new logOut());

        class eventExit implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }
        menu4.addActionListener(new eventExit());

        // Create Calendar object and get current day, month and year
        GregorianCalendar cal = new GregorianCalendar();
        Day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        Month = cal.get(GregorianCalendar.MONTH);
        Year = cal.get(GregorianCalendar.YEAR);
        currentMonth = Month;
        currentYear = Year;

        // Create TableModel and add it to Table
        mdlCalendar = new DefaultTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblCalendar.setModel(mdlCalendar);

        spanel1.add(tblCalendar);
        spanel1.setViewportView(tblCalendar);

        // Add Column consisting of weekdays
        String[] weekdays = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        for (int i = 0; i < 7; i++) {
            mdlCalendar.addColumn(weekdays[i]);
        }

        // Set Table parameters
        tblCalendar.setRowHeight(100);
        mdlCalendar.setColumnCount(7);
        mdlCalendar.setRowCount(6);

        // Update Calendar
        updateCalendar(Month, Year);

        // Forward one month
        nextMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentMonth == 11) {
                    currentMonth = 0;
                    currentYear += 1;
                } else {
                    currentMonth += 1;
                }
                updateCalendar(currentMonth, currentYear);

            }
        });

        // Backward one month
        prevMonth.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (currentMonth == 0) {
                    currentMonth = 11;
                    currentYear -= 1;
                } else {
                    currentMonth -= 1;
                }

                updateCalendar(currentMonth, currentYear);

            }
        });
        addEvent.addActionListener(new ActionListener() {
            /**
             * Invoked when an action occurs.
             *
             * @param e the event to be processed
             */
            @Override
            public void actionPerformed(ActionEvent e) {
                new AddEvents(user);
            }
        });
    }

    private void updateCalendar(int month, int year) {

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        int startMonth, numberDays;

        // Update current month label
        monthCalendar.setText(months[month]);
        yearJlabel.setText(String.valueOf(currentYear));

        // Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mdlCalendar.setValueAt(null, i, j);
            }
        }

        // Get number of days in month
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numberDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);

        // Draw calendar
        for (int i = 1; i <= numberDays; i++) {
            int row = (i + startMonth - 2) / 7;
            int column = (i + startMonth - 2) % 7;
            mdlCalendar.setValueAt(i, row, column);
        }

        // Render Table, set cell colour
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());
    }

    public class tblCalendarRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);
            if (column == 0) {
                setBackground(new Color(255, 220, 220));
            } else {
                setBackground(new Color(255, 255, 255));
            }
            if (value != null) {
                if (Integer.parseInt(value.toString()) == Day && currentMonth == Month && currentYear == Year) {
                    setBackground(new Color(220, 220, 255));
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
        panel1.setLayout(new GridLayoutManager(3, 3, new Insets(0, 0, 0, 0), -1, -1));
        nextMonth = new JButton();
        nextMonth.setText(">>");
        panel1.add(nextMonth, new GridConstraints(1, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        monthCalendar = new JLabel();
        monthCalendar.setAlignmentX(0.0f);
        monthCalendar.setHorizontalAlignment(0);
        monthCalendar.setText("months");
        panel1.add(monthCalendar, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(327, 17), null, 0, false));
        prevMonth = new JButton();
        prevMonth.setText("<<");
        panel1.add(prevMonth, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        spanel1 = new JScrollPane();
        panel1.add(spanel1, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, new Dimension(327, 428), null, 0, false));
        tblCalendar = new JTable();
        spanel1.setViewportView(tblCalendar);
        yearJlabel = new JLabel();
        yearJlabel.setHorizontalAlignment(0);
        yearJlabel.setHorizontalTextPosition(0);
        yearJlabel.setText("year");
        panel1.add(yearJlabel, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        addEvent = new JButton();
        addEvent.setHorizontalAlignment(0);
        addEvent.setHorizontalTextPosition(11);
        addEvent.setText("Add eventLocal");
        panel1.add(addEvent, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}
