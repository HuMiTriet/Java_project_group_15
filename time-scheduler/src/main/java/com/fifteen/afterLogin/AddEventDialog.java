package com.fifteen.afterLogin;

import javax.swing.*;
import java.awt.*;

public class AddEventDialog extends JDialog {

    public AddEventDialog() {

        JDialog addEvent = new JDialog();
        addEvent.setTitle("Add Event");
        addEvent.setSize(500,500);
        addEvent.setModal(true);
        addEvent.setVisible(true);
    }



}
