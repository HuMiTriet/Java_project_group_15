package com.twenty.one.afterLogin;

import javax.swing.*;
import java.awt.*;

public class ShowEventDialog extends JDialog {

    public ShowEventDialog() {

        JDialog addEvent = new JDialog();
        addEvent.setTitle("Current Events");
        addEvent.setSize(500,500);
        addEvent.setModal(true);
        addEvent.setVisible(true);
    }



}

