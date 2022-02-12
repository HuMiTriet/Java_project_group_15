package com.fifteen.events.reminder;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Work in Progress, testing out the java timer and TimerTask functions
 *
 * @author Ante Maric 1273904
 */

public class sendReminders {
    public static void reminder()
    {
        Timer timer = new Timer(); //timer calculates time to execute a task

        //When the timer is up, run is going to be executed inside our TimerTask instance
        TimerTask task = new TimerTask() {
            @Override
            public void run() { //run is an abstract method
                System.out.println("Task is complete!");
            }
        };

        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR,2022);
        date.set(Calendar.MONTH,Calendar.FEBRUARY);
        date.set(Calendar.DAY_OF_MONTH,12);
        date.set(Calendar.HOUR_OF_DAY,16);
        date.set(Calendar.MINUTE,55);
        date.set(Calendar.SECOND,20);
        date.set(Calendar.MILLISECOND,0);

        //timer.schedule(task, 1000);
        timer.schedule(task, date.getTime());

    }
}
