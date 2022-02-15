package com.fifteen.events.reminder;

import com.fifteen.events.local.EventLocal;
import com.fifteen.mailApi.mailUtils;
import com.fifteen.events.reminder.convertOptionToMinute;
import com.fifteen.database.User;


import javax.mail.MessagingException;
import java.util.*;

/**
 * Work in Progress, testing out the java timer and TimerTask functions
 * Delete and edit function are working flawless, reminder need to write a function that checks the real time and the event times from db
 *
 * @author Ante Maric 1273904
 */

//POTENTIAL IDEA?
//event.getDayOfEvent().getTimeInMillis();
//System.currentTimeMillis();

public class sendReminders {
  public static void reminder(EventLocal event)
    {
        //Converting Set to List to make it work in emailUtils;
        Set<String> email = event.getParticipants_email();
        List<String> participants = new ArrayList<>(email);
        for (String k : email){participants.add(k);}

        String subject = "Reminder for upcoming event";

        //timer calculates time to execute a task
        Timer timer = new Timer();
        //When the timer is up, run is going to be executed inside our TimerTask instance
        //Reminder are being sent out depending on their selection inside events
        if(event.getMinutesUntilReminder() == 10)
        {
            //When the reminder is selected to be 10 minutes prior event
            TimerTask reminder1 = new TimerTask() {
                @Override
                public void run() {
                    String body = "Hello, this is a friendly reminder that your event will start in 10 minutes.\n Sincerely,\nTime Scheduler dev team";
                    try {
                        mailUtils.draftEmail(participants, subject, body);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(reminder1, 600000);
        }else if(event.getMinutesUntilReminder() == 60)
        {
            //When the reminder is selected to be 1 hour prior event
            TimerTask reminder2 = new TimerTask() {
                @Override
                public void run() {
                    String body = "Hello, this is a friendly reminder that your event will start in 1 hour.\n Sincerely,\nTime Scheduler dev team";
                    try {
                        mailUtils.draftEmail(participants, subject, body);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(reminder2, 8000000);
        } else if(event.getMinutesUntilReminder() == 4320)
        {
            //When the reminder is selected to be 3 days prior event
            TimerTask reminder3 = new TimerTask() {
                @Override
                public void run() {
                    String body = "Hello, this is a friendly reminder that your event will start in 3 days.\n Sincerely,\nTime Scheduler dev team";
                    try {
                        mailUtils.draftEmail(participants, subject, body);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(reminder3, 85000000);
        } else
        {
            //When the reminder is selected to be 1 Week prior event
            TimerTask reminder4 = new TimerTask() {
                @Override
                public void run() {
                    String body = "Hello, this is a friendly reminder that your event will start in 1 week.\n Sincerely,\nTime Scheduler dev team";
                    try {
                        mailUtils.draftEmail(participants, subject, body);
                    } catch (MessagingException e) {
                        e.printStackTrace();
                    }
                }
            };
            timer.schedule(reminder4, 680000);
        }
    }

    //called when an event has been deleted to notify the user that the event was cancelled
    public static void deleteReminder(EventLocal event, User user) {
        //Converting Set to List to make it work in emailUtils
        Set<String> email = event.getParticipants_email();
        List<String> participants = new ArrayList<>(email);
        for (String k : email){participants.add(k);}
        participants.add(user.getEmail());

        String subject = "Cancellation of upcoming event";
        String body = "Hello, this is a friendly reminder that your event has been cancelled.\n Sincerely,\nTime Scheduler dev team";
        try {
            mailUtils.setupProperties();
            mailUtils.draftEmail(participants, subject, body);
            mailUtils.sendEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //called when an event has been edited to remind the user that something changed
    public static void editReminder(EventLocal event, User user) {
        //Converting Set to List to make it work in emailUtils
        Set<String> email = event.getParticipants_email();
        List<String> participants = new ArrayList<>(email);
        for (String k : email){participants.add(k);}
        participants.add(user.getEmail());

        String subject = "Your upcoming event has been edited";
        String body = "Hello, this is a friendly reminder that your event has been edited.\n Sincerely,\nTime Scheduler dev team";
        try {
            mailUtils.setupProperties();
            mailUtils.draftEmail(participants, subject, body);
            mailUtils.sendEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public static void changeEmail(String newEmail, String code) {
        List<String> participants = new ArrayList<>();
        participants.add(newEmail);

        String subject = "Verification Code for Time Scheduler";
        String body = "Hello, you requested to change your e-mail.\n Please enter the following code\n" + code +"\n";
        try {
            mailUtils.setupProperties();
            mailUtils.draftEmail(participants, subject, body);
            mailUtils.sendEmail();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
