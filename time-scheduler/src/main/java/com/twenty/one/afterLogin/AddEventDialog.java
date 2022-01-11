package com.twenty.one.afterLogin;

import javax.swing.*;
import java.awt.*;

public class AddEventDialog extends JDialog {

    public AddEventDialog() {

        JDialog addEvent = new JDialog();
        addEvent.setTitle("Add Event");
        addEvent.setSize(200,200);
        addEvent.setModal(true);
        addEvent.setVisible(true);
    }



}
