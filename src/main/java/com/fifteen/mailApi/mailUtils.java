package com.fifteen.mailApi;

/**
 * Establishing a connection with an email provider (in this case gmail) and
 * sending an email from the user that created an event to all the participants (reminder, changes for event etc.)
 *
 * IMPORTANT NOTE: Settings need to be changed to "Less secure app access: ON" for the emails to be received
 *
 *
 * @author Ante Maric 1273904
 */

import javax.mail.*;
import javax.mail.internet.*;
import java.util.List;
import java.util.Properties;

public class mailUtils {
  static Session newSession = null;
  static MimeMessage mimeMessage = null;
  static String emailAddressSender = "javacomtwentyone@gmail.com";
  static String passwordSender = "StrongPassword21";

    public static void setupProperties() {
    System.out.println("Preparing to send email");
    Properties properties = System.getProperties(); // assign key and value to property

    // Setting up the properties to connect to the gmail servers
    // SMTP = Simple Mail Transfer Protocol (most common protocol to send email messages)
    properties.put("mail.smtp.auth", "true");               // Set authentication to true (standard is false) so the session can attempt to connect to authenticate the user
    properties.put("mail.smtp.starttls.enable", "true");    // Starts the STARTTLS command on the server which switches to a TLS protected connection
    properties.put("mail.smtp.host", "smtp.gmail.com");     // Gmail host location - smtp server to connect to
    properties.put("mail.smtp.port", "587");                // Port for TLS connection - smtp server port to connect to (gmail is 587)
    properties.put("mail.transport.protocol", "smtp");      // Specifies the default transport protocol, in this case it's SMTP

    // creating a new session
    // Authenticator - abstract class that represents an object that knows how to obtain information for a network connection
    // getPasswordAuthentication is called when a password authorization is needed. (In this case when we sign in to the gmail account)
    newSession = Session.getDefaultInstance(properties, new Authenticator() {
      @Override
      protected PasswordAuthentication getPasswordAuthentication() {
        return new PasswordAuthentication(emailAddressSender, passwordSender);
      }
    });
  }

  mailUtils mail = new mailUtils();

  //MIME = Multipurpose Internet Mail Extension - Defines the content that an email is going to have
  public static MimeMessage draftEmail(List<String> emailAddressReceiver, String emailSubject, String emailBody) throws MessagingException {
      mimeMessage = new MimeMessage(newSession);


      //for loop that adds all the recipients that were passed in as parameters in draftEmail to receive the email
      for (int i = 0; i < emailAddressReceiver.size(); i++) {
        mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailAddressReceiver.get(i)));
      }

      mimeMessage.setSubject(emailSubject);
      MimeMultipart multiPart = new MimeMultipart();

      MimeBodyPart bodyPart = new MimeBodyPart();
      bodyPart.setContent(emailBody, "text/plain");
      multiPart.addBodyPart(bodyPart);
      mimeMessage.setContent(multiPart);
      return mimeMessage;
  }

  // Send an email from admin account to all the
  public static void sendEmail() throws MessagingException {
    String emailAddressSender = "TimeScheduler15A@gmail.com"; // "Admin gmail address from which all reminders/changes will be sent out to the recipients (Users)
    String passwordSender = "cUeLjgBu3bDM6Ek7";     // Admin gmail password
    String emailHost = "smtp.gmail.com";            // gmail host
    Transport transport = newSession.getTransport("smtp"); // get object of the transport class using the new session that is created
    transport.connect(emailHost, emailAddressSender, passwordSender); // connecting to the account that the emails will send from
    transport.sendMessage(mimeMessage, mimeMessage.getAllRecipients()); // send the message to all the recipients from MimeMessage Method
    transport.close(); // close email transport
    System.out.println("Email sent successfully!"); // Will be printed out on the cmd line if executed successful
  }
}