package com.fifteen.mailApi;

/**
 * simple program to establish a connection with an email provider (in this case gmail) and
 * send an email to that adress
 *
 * I will comment it out as soon as I can so you guys can understand what's happening
 *
 * @author Ante
 */

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mailUtils {
    public static void sendMail(String recipient) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties(); //assign key and value to property

        //Setting up the data to connect to the gmail servers
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String emailAddress = "javacomtwentyone@gmail.com";
        String password = "StrongPassword21";

        //Session represents the connection to the mailserver
        //It contains the server data and an authentication object
        Session session = Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(emailAddress, password);
                    }
                });

        Message message = prepareMessage(session, emailAddress, recipient);

        //Transport modulates the transport-mechanism for the process of sending the email
        Transport.send(message);
        System.out.println("Message sent SUCCESSFULLY");
    }
    //Message - abstract class which is used to modulate/build the message
    private static Message prepareMessage(Session session, String emailAddress, String recipient) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emailAddress));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("THIS CODE WORKS WOHOOOO");
            message.setText("Hey There, \n This is a reminder email for the upcoming event");
            return message;
        } catch (Exception e) {
            Logger.getLogger(mailUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
}
