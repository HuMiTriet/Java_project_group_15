package com.twenty.one.database;

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

	
	public Events(String eventName, String description, int startTime, int endTime,
			String location, int priority, int reminder, int day, int month, int year) {
		
		this.eventName = eventName;
		this.description = description;
		this.startTime = startTime;
		this.endTime = endTime;
		this.location = location;
		this.priority = priority;
		this.reminder = reminder;
		this.day = day;
		this.month = month;
		this.year = year;
	}


	public String getEventName() {
		
		return eventName;
	}
	
	public String getDescription() {
		
		return description;
	}
	
	public int getStartTime() {
		
		return startTime;
	}
	
	public int getEndTime() {
		
		return endTime;
	}
	
	public String getLocation() {
		
		return location;
	}

	public int getPriority() {
		
		return priority;
	}

	public int getReminder() {
		
		return reminder;
	}
	
	public int getDay() {
		
		return day;
	}
	
	public int getMonth() {
		
		return month;
	}
	
	public int getYear() {
		
		return year;
	}

	public void setEventName(String eventName) {
		
		this.eventName = eventName;
	}

	public void setDescription(String description) {
		
		this.description = description;
	}

	public void setStartTime(int startTime ) {
		
		this.startTime = startTime;
	}

	public void setEndTime(int endTime) {
		
		this.endTime = endTime;
	}


	public void setLocation(String location) {
		
		this.location = location;
	}

	public void setPriority(int priority) {
		
		this.priority = priority;
	}

	public void setReminder(int reminder) {
		
		this.reminder = reminder;
	}

	public void setDay(int day) {
		
		this.day = day;
	}
	
	public void setMonth(int month) {
		
		this.month = month;
	}
	
	public void setYear(int year) {
		
		this.year = year;
	}
}

