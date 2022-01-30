//package com.fifteen;
//
//import java.util.Calendar;
//import java.util.HashSet;
//import java.util.Set;
//
//import com.fifteen.events.local.CheckDate;
//import com.fifteen.events.local.EventLocal;
//// import com.fifteen.events.local.JsonWriter;
//import com.fifteen.events.local.Location;
//import com.fifteen.events.local.Priority;
//
//import org.junit.jupiter.api.Test;
//
//public class JsonWriterTest {
//
//  @Test
//  void writeToLocalData() {
//    Set<String> par = new HashSet<String>();
//    par.add("\"Tim\"");
//    par.add("\"Ante\"");
//    par.add("\"PJ\"");
//    par.add("\"PJ\"");
//
//    Calendar bday = CheckDate.validate("13:15 02/02/1994");
//
//    Location location = new Location("FFM", 1.0, 1.0);
//    Priority pri = Priority.HIGH;
//    EventLocal event = new EventLocal("B", "test", bday, par, location, pri);
//
//    // JsonWriter.addEventToLocalData(event);
//  }
//}
