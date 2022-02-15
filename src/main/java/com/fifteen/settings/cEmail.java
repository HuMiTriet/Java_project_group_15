package com.fifteen.settings;

import com.fifteen.auth.security.UserAuthenticator;
import com.fifteen.database.DBMethod;
import com.fifteen.database.User;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import com.fifteen.events.reminder.sendReminders;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.UUID;

public class cEmail extends JFrame {
    private JFrame frame;
    private JPanel panel1;
    private JTextField inNewUN;
    private JTextField enterOldEmailTextField;
    private JTextField enterNewEmailTextField;
    private JTextField enterVerificationCodeTextField;
    private JButton submitEmail;
    private JButton submitButton;
    private JLabel emailLabel;
    private JLabel changeLabel;
    private JLabel codeVer;

    public cEmail(User user) {


        frame = new JFrame("Email Change");

        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.setPreferredSize(new Dimension(300, 350));
        frame.setResizable(false);

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String oldEmail = enterOldEmailTextField.getText();
                String newEmail = enterNewEmailTextField.getText();
                String codeIN = enterVerificationCodeTextField.getText();
                boolean fieldCheck = true;

                DBMethod.createConnection();

                //checks email field empty
                if (UserAuthenticator.checkFieldEmpty(emailLabel, oldEmail, "Please enter your current email") == false)
                    fieldCheck = false;

                //checks email is current email
                String emailTest = user.getEmail();
                //System.out.println(emailTest.equals(oldEmail));
                if (emailTest.equals(oldEmail) == false) {
                    emailLabel.setText("Incorrect email");
                    fieldCheck = false;
                }

                //checks new email field empty
                if (UserAuthenticator.checkFieldEmpty(changeLabel, newEmail, "Please enter your new email") == false)
                    fieldCheck = false;

                //check new email in DB
                if (UserAuthenticator.authenticateEmailField(changeLabel, newEmail, "This email is already taken") == true)
                    fieldCheck = false;

                if (UserAuthenticator.checkFieldEmpty(codeVer, codeIN, "Please enter your verification code") == false)
                fieldCheck = false;


                if (fieldCheck == true) {
                    try {
                        DBMethod.changeFieldExisted(user, newEmail, 'e');
                        JOptionPane.showMessageDialog(frame, "Email updated !", "Success",
                                JOptionPane.INFORMATION_MESSAGE);

                        frame.dispose();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                }

            }

        });

        frame.add(panel1);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        submitEmail.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String oldEmail = enterOldEmailTextField.getText();
                String newEmail = enterNewEmailTextField.getText();
                String codeIN = enterVerificationCodeTextField.getText();
                boolean fieldCheck = true;

                DBMethod.createConnection();

                //checks email field empty
                if (UserAuthenticator.checkFieldEmpty(emailLabel, oldEmail, "Please enter your current email") == false)
                    fieldCheck = false;

                //checks email is current email
                String emailTest = user.getEmail();
                //System.out.println(emailTest.equals(oldEmail));
                if (emailTest.equals(oldEmail) == false) {
                    emailLabel.setText("Incorrect email");
                    fieldCheck = false;
                }

                //checks new email field empty
                if (UserAuthenticator.checkFieldEmpty(changeLabel, newEmail, "Please enter your new email") == false)
                    fieldCheck = false;

                //check new email in DB
                if (UserAuthenticator.authenticateEmailField(changeLabel, newEmail, "This email is already taken") == true)
                    fieldCheck = false;

                if (UserAuthenticator.checkFieldEmpty(codeVer, codeIN, "Please enter your verification code") == false)
                    fieldCheck = false;

                //Code Generator
                String codeS = UUID.randomUUID().toString();
                sendReminders.changeEmail(newEmail, codeS.substring(0, 4));

                if(codeS.equals(codeIN)== false)
                    fieldCheck = false;
            }
        });

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
        panel1.setLayout(new GridLayoutManager(11, 3, new Insets(0, 0, 0, 0), -1, -1));
        enterOldEmailTextField = new JTextField();
        enterOldEmailTextField.setText("");
        panel1.add(enterOldEmailTextField, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        enterNewEmailTextField = new JTextField();
        enterNewEmailTextField.setText("");
        panel1.add(enterNewEmailTextField, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(2, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        enterVerificationCodeTextField = new JTextField();
        enterVerificationCodeTextField.setText("");
        panel1.add(enterVerificationCodeTextField, new GridConstraints(7, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final Spacer spacer2 = new Spacer();
        panel1.add(spacer2, new GridConstraints(4, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer3 = new Spacer();
        panel1.add(spacer3, new GridConstraints(7, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer4 = new Spacer();
        panel1.add(spacer4, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer5 = new Spacer();
        panel1.add(spacer5, new GridConstraints(4, 2, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
        final Spacer spacer6 = new Spacer();
        panel1.add(spacer6, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final JLabel label1 = new JLabel();
        label1.setHorizontalAlignment(0);
        label1.setHorizontalTextPosition(0);
        label1.setText("The verification code will be sent to your email");
        panel1.add(label1, new GridConstraints(9, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer7 = new Spacer();
        panel1.add(spacer7, new GridConstraints(10, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        submitEmail = new JButton();
        submitEmail.setText("Send Code");
        panel1.add(submitEmail, new GridConstraints(5, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        submitButton = new JButton();
        submitButton.setText("Submit");
        panel1.add(submitButton, new GridConstraints(8, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        emailLabel = new JLabel();
        emailLabel.setText("Email");
        panel1.add(emailLabel, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        changeLabel = new JLabel();
        changeLabel.setText("New Email");
        panel1.add(changeLabel, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        codeVer = new JLabel();
        codeVer.setText("Enter Code Verification");
        panel1.add(codeVer, new GridConstraints(6, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

}