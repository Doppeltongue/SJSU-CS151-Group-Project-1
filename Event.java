package scheduler;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * @authors Phillip, Ricardo, Tri
 * 
 * Event class that holds an event on the calendar
 */

@SuppressWarnings("rawtypes")
public class Event implements Comparable{
	public static final String[] TYPES = {"Class", "Work", "Other"}; 
	private int type;
	private Calendar startTime = Calendar.getInstance(); //starting time of the event
	private int duration; //duration of event in minutes
	private String description; //description of the event
	
	public Event(Date time, int duration, int type,String description) {
		this.startTime = new GregorianCalendar();
		startTime.setTime(time);
		this.duration = duration;
		this.type = type;
		this.description = description;
	}
	
	/**
	 * The natural order of an event is based on its time
	 */
	@Override
	public int compareTo(Object o) {
		Event e = (Event) o;
		return startTime.getTime().compareTo(e.getStartTime());
	}

	
	//Past this point are the getters and setters for the variables
	
	public Date getStartTime() {
		return startTime.getTime();
	}

	@Override
	public String toString() {
		return startTime.getTimeInMillis()+" " + duration + " " + type+" "+description;
	}

	public void setStartTime(Date time) {
		startTime.setTime(time);
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDay() {
		return startTime.get(Calendar.DATE);
	}
	
	public int getMonth() {
		return startTime.get(Calendar.MONTH);
	}

	public String getTime() {
		return startTime.get(Calendar.HOUR_OF_DAY)+":"+startTime.get(Calendar.MINUTE);
	}
	
	public int getHour() {
		return startTime.get(Calendar.HOUR_OF_DAY);
	}
	
	public String getDate() {
		return (getMonth()+1)+"/"+startTime.get(Calendar.DAY_OF_MONTH)+"/"+(startTime.get(Calendar.YEAR));
	}
	
	public int getType() {
		return type;
	}
	
	public String getTypeName() {
		return TYPES[type];
	}
	
	public Calendar getCalendar() {
		return startTime;
	}
	
	public int getEndDay() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime.getTime());
		cal.add(Calendar.MINUTE, duration);
		return cal.get(Calendar.DATE);
	}
	
	public Date getEndTime() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(startTime.getTime());
		cal.add(Calendar.MINUTE, duration);
		return cal.getTime();
	}
	
}
