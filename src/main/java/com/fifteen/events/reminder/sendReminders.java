//package com.fifteen.events.reminder;
//
//import com.fifteen.events.local.EventLocal;
//import com.fifteen.mailApi.mailUtils;
//
//import javax.mail.MessagingException;
//import java.util.Calendar;
//import java.util.List;
//import java.util.Set;
//import java.util.Timer;
//import java.util.TimerTask;
//
///**
// * Work in Progress, testing out the java timer and TimerTask functions
// *
// * @author Ante Maric 1273904
// */
//
//public class sendReminders {
//  public static void reminder(EventLocal event)
//    {
//        String subject = "Reminder for upcoming event";
//        Set<String> participants = event.getParticipants_email();
//
//        Timer timer = new Timer(); //timer calculates time to execute a task
//        //When the timer is up, run is going to be executed inside our TimerTask instance
//        //Reminder are being sent out depending on their selection inside events
//        if()
//        {
//            //When the reminder is selected to be 10 minutes prior event
//            TimerTask reminder1 = new TimerTask() {
//                @Override
//                public void run() {
//                    String body = "Hello, this is a friendly reminder that your event will start in 10 minutes.\n Sincerely,\nTime Scheduler dev team";
//                    try {
//                        mailUtils.draftEmail(participants, subject, body);
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            timer.schedule(reminder1, date.getTime());
//        }else if()
//        {
//            //When the reminder is selected to be 1 hour prior event
//            TimerTask reminder2 = new TimerTask() {
//                @Override
//                public void run() {
//                    String body = "Hello, this is a friendly reminder that your event will start in 1 hour.\n Sincerely,\nTime Scheduler dev team";
//                    try {
//                        mailUtils.draftEmail(participants, subject, body);
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            timer.schedule(reminder2, date.getTime());
//        } else if()
//        {
//            //When the reminder is selected to be 3 days prior event
//            TimerTask reminder3 = new TimerTask() {
//                @Override
//                public void run() {
//                    String body = "Hello, this is a friendly reminder that your event will start in 3 days.\n Sincerely,\nTime Scheduler dev team";
//                    try {
//                        mailUtils.draftEmail(participants, subject, body);
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            timer.schedule(reminder3, date.getTime());
//        } else
//        {
//            //When the reminder is selected to be 1 Week prior event
//            TimerTask reminder4 = new TimerTask() {
//                @Override
//                public void run() {
//                    String body = "Hello, this is a friendly reminder that your event will start in 1 week.\n Sincerely,\nTime Scheduler dev team";
//                    try {
//                        mailUtils.draftEmail(participants, subject, body);
//                    } catch (MessagingException e) {
//                        e.printStackTrace();
//                    }
//                }
//            };
//            timer.schedule(reminder4, date.getTime());
//        }
//
//       /* Calendar date = Calendar.getInstance();
//        date.set(Calendar.YEAR,2022);
//        date.set(Calendar.MONTH,Calendar.FEBRUARY);
//        date.set(Calendar.DAY_OF_MONTH,12);
//        date.set(Calendar.HOUR_OF_DAY,16);
//        date.set(Calendar.MINUTE,55);
//        date.set(Calendar.SECOND,20);
//        date.set(Calendar.MILLISECOND,0);*/
//
//        //timer.schedule(task, 1000);
//
//
//
//
//
//    }
//}
