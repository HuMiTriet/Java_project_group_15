package com.fifteen.events.local;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Class to store the location of an event.
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
}
