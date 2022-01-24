package com.fifteen.mailApi;

/**
 * Establishing a connection with an email provider (in this case gmail) and
 * sending an email from the user that created an event to all the participants (reminder, changes for event etc.)
 *
 *
 * @author Ante
 */

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class mailUtils {
    Session newSession = null;
    MimeMessage mimeMessage = null;

    public void setupProperties()  {
        System.out.println("Preparing to send email");
        Properties properties = System.getProperties(); //assign key and value to property

        //Setting up the properties to connect to the gmail servers
        properties.put("mail.smtp.auth", "true"); //Set authentication for Google
        properties.put("mail.smtp.starttls.enable", "true"); //Start TLS connection
        properties.put("mail.smtp.host", "smtp.gmail.com"); //Gmail host location
        properties.put("mail.smtp.port", "587"); //Port for TLS connection

        newSession = Session.getDefaultInstance(properties, null); //creating a new session
    }

    mailUtils mail = new mailUtils();
    //mail.draftEmail();
    //mail.sendEmail();

    private MimeMessage draftEmail() throws AddressException, MessagingException {
        String[] emailAdress = {"pj@gmail.com", "ante@gmail.com", "jorge@gmail.com", "tim@gmail.com"};
        String emailSubject = "Reminder for your upcoming event";
        String emailBody = "Hey There,\nThis is a reminder that your event INSERT_NAME will start in 12345 mins/hours/days/weeks";
        String[] password = {"AnimeMan69", "CoffeAddict11", "MavenLover123", "SleepIsLife777"};
        mimeMessage = new MimeMessage(newSession);

        for (int i=0; i<emailAdress.length; i++){
            mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAdress[i]));
        }

        mimeMessage.setSubject(emailSubject);
        MimeMultipart multiPart = new MimeMultipart();

        MimeBodyPart bodyPart = new MimeBodyPart();
        bodyPart.setContent(emailBody, "html/text");
        multiPart.addBodyPart(bodyPart);
        mimeMessage.setContent(multiPart);
        return mimeMessage;
    }

    //Send an email from admin account to all the
    private void sendEmail() throws MessagingException {
        String fromUser = "User that created event [username] => admin@gmail.com";
        String fromUserPassword = "User that created event [password] = > *******";
        String emailHost = "smtp.gmail.com";
        Transport transport = newSession.getTransport("smtp"); //get object of the transport class using the new session that is created
        transport.connect(emailHost, fromUser, fromUserPassword); //connecting to the account that the emails will be sent from
        transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients()); //send the message and all their recipients from MimeMessage Method
        transport.close(); //close email transport
        System.out.println("Email sent successfully!");
    }

}

/*
                                            ********* OLD CODE *********

String emailAddress = //"javacomtwentyone@gmail.com";
        String password = //"StrongPassword21";

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
            message.setSubject("Reminder for your upcoming event");
            message.setText("Hey There,\nThis is a reminder that your event will start in 12345 mins/hours/days/weeks");
            return message;
        } catch (Exception e) {
            Logger.getLogger(mailUtils.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }*/



