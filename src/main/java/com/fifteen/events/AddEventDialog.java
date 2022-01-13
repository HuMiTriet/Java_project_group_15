package com.fifteen.events;

import javax.swing.*;

public class AddEventDialog extends JDialog {

    public AddEventDialog() {

        JDialog addEvent = new JDialog();
        addEvent.setTitle("Add Event");
        addEvent.setSize(500,500);
        addEvent.setModal(true);
        addEvent.setVisible(true);
    }



}
