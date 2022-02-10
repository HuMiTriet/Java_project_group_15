package com.fifteen.events.reminder;

public class convertMinutesToOption {
    public static String convert(int minute) {
        String option = "0";
        switch (minute) {
            case 10:
                option = "10 minutes";
                break;
            case 60:
                option = "1 hour";
                break;
            case 4320:
                option = "3 days";
                break;
            case 10080:
                option = "1 week";
                break;
        }
        return option;
    }
}
