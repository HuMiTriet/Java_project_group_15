package com.fifteen.events.reminder;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @author Ante Maric 1273904
 */

public class sendReminders {
    public static int convert(String option)
    {
        Timer timer = new Timer(); //timer calculates time to execute a task

        //When the timer is up, run is going to be executed inside our TimerTask instance
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                System.out.println("Task is complete!");
            }
        };

        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR,2022);
        date.set(Calendar.MONTH,Calendar.JANUARY);
        date.set(Calendar.DAY_OF_MONTH,10);
        //timer.schedule(task, 1000);

        int minute = 0;
        return minute;
    }
}
