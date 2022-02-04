package com.fifteen.profile;

import com.fifteen.auth.security.UserAuthenticator;
import com.fifteen.events.local.localDbMethod;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AddContact extends JFrame {
  private JFrame frame;
  private JPanel panel1;
  private JLabel prompt;
  private JTextArea emailText;
  private JButton submit;
  private JLabel emailLabel;

  public AddContact() {
    frame = new JFrame("New Contact");

    frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    frame.setPreferredSize(new Dimension(300, 150));
    frame.setResizable(false);

    frame.add(panel1);
    frame.pack();
    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
    submit.addActionListener(new ActionListener() {
      /**
       * Invoked when an action occurs.
       *
       * @param e the event to be processed
       */
      @Override
      public void actionPerformed(ActionEvent e) {
        boolean allFieldsCorrect = true;

        String emailString = emailText.getText();
        allFieldsCorrect = UserAuthenticator.checkEmailFormat(emailLabel, emailString);

        if (allFieldsCorrect) {
          try {
            localDbMethod.addToContactsTable(emailString);
            emailText.setText("");
          } catch (SQLException error) {
            error.printStackTrace();
          }
        }

      }
    });
  }

}
