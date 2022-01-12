package com.fifteen.afterLogin;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Events {

	private String eventName;
	private String description;
	private int startTime;
	private int endTime;
	private String location;
	private int priority;
	private int reminder;
	private int day;
	private int month;
	private int year;
	private String mail;
	private String participants;
}

