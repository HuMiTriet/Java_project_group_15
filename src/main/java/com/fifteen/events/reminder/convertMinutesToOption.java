package com.fifteen.events.reminder;

public class convertMinutesToOption {
    /**
     * Function to reverse the minutes back to the option
     * in reminder picker
     * @param minute - month used for the calendar
     * @return String - option for the reminderPicker
     * @author Tim Görß 1252200
     */

    public static String convert(int minute) {
        String option = switch (minute) {
            case 10 -> "10 minutes";
            case 60 -> "1 hour";
            case 4320 -> "3 days";
            case 10080 -> "1 week";
            default -> "0";
        };
        return option;
    }
}
