package com.fifteen.events;

import com.fifteen.auth.login.LoginPage;
import com.fifteen.database.User;
import com.fifteen.events.local.localDb;
import com.fifteen.events.local.localDbMethod;
import com.fifteen.events.local.exportImport.FileTypeFilter;
import com.fifteen.events.local.exportImport.exportTxt;
import com.fifteen.events.local.EventLocal;
import com.fifteen.events.settings.EventSettings;
import com.fifteen.profile.ProfilePage;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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
    private JList upcomEvents;
    private JPanel panel2;
    private JLabel upcomingEventLable;
    private JButton refreshBtn;
    private DefaultTableModel mdlCalendar;
    private DefaultListModel mdlList;
    private JFrame frame;
    private int Day, Month, Year, currentMonth, currentYear;

    // @author Jorge
    private JMenuBar e_menuBar;
    private JMenu menu;
    private JMenuItem menu1, menu2, menu3, menu4;
    private JMenu sview;
    private JMenuItem sview1, sview2, sview3;
    private JMenu export;
    private JMenuItem txt;
    private JMenu database;
    private JMenuItem dimport, dexport;
    private JMenu about;
    private JMenuItem about1;

    public CalendarView(User user) {

        // Create frame
        frame = new JFrame("Calendar View");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(1280, 720));
        frame.setResizable(false);

        e_menuBar = new JMenuBar();

        // create menus
        menu = new JMenu("Menu");
        sview = new JMenu("Switch View");
        export = new JMenu("Export");
        database = new JMenu("Database");
        about = new JMenu("About");

        // create menuitems
        menu1 = new JMenuItem("Profile");
        menu2 = new JMenuItem("Settings");
        menu3 = new JMenuItem("Log Out");
        menu4 = new JMenuItem("Exit");

        sview1 = new JMenuItem("Daily");
        sview2 = new JMenuItem("Weekly");
        sview3 = new JMenuItem("Monthly");

        txt = new JMenuItem(".txt");

        dimport = new JMenuItem("Import Database");
        dexport = new JMenuItem("Export Database");

        about1 = new JMenuItem("Documentation");

        // add menu items to menu
        menu.add(menu1);
        menu.add(menu2);
        menu.add(menu3);
        menu.add(menu4);

        sview.add(sview1);
        sview.add(sview2);
        sview.add(sview3);

        export.add(txt);

        database.add(dimport);
        database.add(dexport);

        about.add(about1);

        // add menu to menu bar
        e_menuBar.add(menu);
        e_menuBar.add(sview);
        e_menuBar.add(export);
        e_menuBar.add(database);
        e_menuBar.add(about);

        // add menubar to frame
        frame.setJMenuBar(e_menuBar);

        // Add panel
        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        class openProfile implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ProfilePage();
            }
        }
        menu1.addActionListener(new openProfile());

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
                localDb.closeLocalConnection();
            }
        }
        menu4.addActionListener(new eventExit());

        class addExportAction implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // new AddContact();
                JFileChooser fileChooser = new JFileChooser();

                fileChooser.setFileFilter(new FileTypeFilter(".txt", "Text File"));
                int respond = fileChooser.showSaveDialog(null);

                if (respond == JFileChooser.APPROVE_OPTION) {
                    try {
                        String content = localDbMethod.getExportContent();
                        File file = fileChooser.getSelectedFile();
                        exportTxt.writeEvents(file, content);
                    } catch (IOException e1) {
                        e1.printStackTrace();
                    } catch (SQLException e2) {
                        e2.printStackTrace();
                    }
                }

            }
        }
        txt.addActionListener(new addExportAction());

        // Create Calendar object and get current day, month and year
        GregorianCalendar cal = new GregorianCalendar();
        Day = cal.get(GregorianCalendar.DAY_OF_MONTH);
        Month = cal.get(GregorianCalendar.MONTH);
        Year = cal.get(GregorianCalendar.YEAR);
        currentMonth = Month;
        currentYear = Year;

        // Initialize JList
        mdlList = new DefaultListModel();
        upcomEvents.setModel(mdlList);

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
                new AddEvents(user, Day, Month, Year, CalendarView.this);
                updateCalendar(currentMonth, currentYear);
            }
        });

        // Clicking event cell opens ShowEventFrame
        spanel1.addMouseListener(new MouseAdapter() {
        });
        tblCalendar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);

                JTable tblCalender = (JTable) e.getSource();
                int column = tblCalender.getSelectedColumn();
                int row = tblCalender.getSelectedRow();

                if (mdlCalendar.getValueAt(row, column) != null) {
                    int day = (Integer) mdlCalendar.getValueAt(row, column);

                    new ShowEvents(user, day, currentMonth, currentYear, CalendarView.this);
                }

            }
        });

        refreshBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateCalendar(currentMonth, currentYear);
            }
        });
    }

    public void updateCalendar(int month, int year) {

        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September",
                "October", "November", "December"};
        ArrayList<Integer> daysWithEvent = new ArrayList<>();
        int startMonth, numberDays;

        // Get Events
        ArrayList<EventLocal> eventMonths = new ArrayList<>();

        try {
            eventMonths = localDbMethod.buildEventLocal(month, year);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Get days with events
        /*
         * if (eventMonths.isEmpty() != true) {
         * for (int i = 0; i < eventMonths.size(); i++) {
         * daysWithEvent.add(eventMonths.get(i).getDayOfEvent().get(GregorianCalendar.
         * DATE));
         * }
         * }
         */

        // Update current month label
        monthCalendar.setText(months[month]);
        yearJlabel.setText(String.valueOf(currentYear));

        // Clear table
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                mdlCalendar.setValueAt(null, i, j);
            }
        }

        // Clear list
        mdlList.removeAllElements();

        // Get number of days in month
        GregorianCalendar cal = new GregorianCalendar(year, month, 1);
        numberDays = cal.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
        startMonth = cal.get(GregorianCalendar.DAY_OF_WEEK);

        // Draw calendar
        for (int i = 1; i <= numberDays; i++) {
            int row = (i + startMonth - 2) / 7;
            int column = ((i + startMonth) - 2) % 7;
            mdlCalendar.setValueAt(i, row, column);
        }

        // Add upcoming events in month
        if (eventMonths.isEmpty() != true) {
            for (int i = 0; i < eventMonths.size(); i++) {
                mdlList.addElement(
                        eventMonths.get(i).getEventName() + " " + eventMonths.get(i).getDayOfEvent().get(GregorianCalendar.DATE)
                                + " " + eventMonths.get(i).getPriority());
                mdlList.addElement(" ");
            }
        }
        // Render Table, set cell colour
        tblCalendar.setDefaultRenderer(tblCalendar.getColumnClass(0), new tblCalendarRenderer());

    }

    public class tblCalendarRenderer extends DefaultTableCellRenderer {
        public Component getTableCellRendererComponent(JTable table, Object value, boolean selected, boolean focused,
                                                       int row, int column) {
            super.getTableCellRendererComponent(table, value, selected, focused, row, column);

            int priorityOfDay;
            int temp;

            // Get Events
            ArrayList<EventLocal> eventMonths = new ArrayList<>();

            try {
                eventMonths = localDbMethod.buildEventLocal(currentMonth, currentYear);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            setBackground(new Color(255, 255, 255));

            if (value != null) {

                priorityOfDay = 0;

                for (int i = 0; i < eventMonths.size(); i++) {

                    if (Integer.parseInt(value.toString()) == eventMonths.get(i).getDayOfEvent().get(GregorianCalendar.DATE)) {

                        if (eventMonths.get(i).getPriority().equals("high")) {

                            temp = 3;

                            if (temp > priorityOfDay) {

                                priorityOfDay = temp;
                                setBackground(new Color(236, 9, 9));
                            }

                        }
                        else if (eventMonths.get(i).getPriority().equals("medium")) {

                            temp = 2;

                            if (temp > priorityOfDay) {

                                priorityOfDay = temp;
                                setBackground(new Color(208, 196, 65));
                            }
                        }
                        else if (eventMonths.get(i).getPriority().equals("low")) {

                            temp = 1;

                            if (temp > priorityOfDay) {

                                priorityOfDay = temp;
                                setBackground(new Color(119, 206, 13));
                            }

                        }

                    }
                }
            }

            /*
             * if (value != null) {
             * if (Integer.parseInt(value.toString()) == Day && currentMonth == Month &&
             * currentYear == Year) {
             * setBackground(new Color(220, 220, 255));
             * }
             * }
             */

            setForeground(Color.black);

            return this;
        }

    }

}
