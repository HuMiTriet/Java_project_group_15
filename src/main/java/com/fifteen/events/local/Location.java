package com.fifteen.events.local;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class to store the location and a function to calculate the distance between
 * two Location class. This class is taken from Princeton University's cs class
 * please check the link to the orginal code.
 * 
 * @see https://introcs.cs.princeton.edu/java/44st/Location.java.html
 * 
 * @author Triet Huynh
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Location {
  private String name = "NA";
  private double longitude = 0.0;
  private double latitude = 0.0;

  public double distanceTo(Location that) {
    double KILOMETER_PER_NAUTICAL_MILE = 1.852;
    double lat1 = Math.toRadians(this.latitude);
    double lon1 = Math.toRadians(this.longitude);
    double lat2 = Math.toRadians(that.latitude);
    double lon2 = Math.toRadians(that.longitude);

    // great circle distance in radians, using law of cosines formula
    double angle = Math.acos(Math.sin(lat1) * Math.sin(lat2)
        + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon1 - lon2));

    // each degree on a great circle of Earth is 60 nautical miles
    double nauticalMiles = 60 * Math.toDegrees(angle);
    double kilometers = KILOMETER_PER_NAUTICAL_MILE * nauticalMiles;
    return kilometers;
  }
}
