package scheduler;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

import javax.swing.JFrame;



/**
 * 
 * @author Phillip, Ricardo, Tri
 * 
 * Database of the Calendar implemented with an ArrayList
 * All methods that alter the database will automatically save any changes
 */
public class ScheduleController {
	private static final String SAVE_LOCATION = new String("schedule.txt");
	private static final int MILLISECONDS_IN_HOUR = 3600000;
	static ArrayList<Event> database; //ArrayList is used since we don't know what the database size will be
	static JFrame foundation = new JFrame();
	
	public ScheduleController() {
		database = new ArrayList<Event>();
	}
	
	/**
	 * The save format is int(space)int(space)String
	 */
	public void load(){
		Path path = Paths.get(SAVE_LOCATION);
		try {
			Scanner s = new Scanner(path);
			while(s.hasNext()) {
				long time = s.nextLong();
				Date start = new Date(time);
				int duration = s.nextInt();
				int type = s.nextInt();
				String description = s.nextLine();
				database.add(new Event(start, duration, type, description));
			}
			s.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Not sure if this is neccessary yet
	public static void sort() {
		EventComparator comp = new EventComparator();
		database.sort(comp);
	}
	
	/**
	 * Method to save the database to a file
	 */
	public static void save() {
		sort();
		Path path = Paths.get(SAVE_LOCATION);
		try {
			BufferedWriter writer = Files.newBufferedWriter(path);
			for (Event v: database) {
				writer.write(v.toString());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void add(Event e) {
		database.add(e);
		save();
	}
	
	public static void remove(Event e) {
		database.remove(e);
		save();
	}
	
	public ArrayList<Event> getDay(Date date) {
		ArrayList<Event> day = new ArrayList<Event>();
		for (Event e: database) {
			if (Math.abs(e.getStartTime().getTime()-date.getTime())<MILLISECONDS_IN_HOUR) {
				day.add(e);
			}
		}
		return day;
	}
	
	public ArrayList<Event> getWeek(Date date) {
		GregorianCalendar cal = new GregorianCalendar();
		cal.setTime(date);
		ArrayList<Event> week = new ArrayList<Event>();
		for (Event e: database) {
			if (e.getCalendar().get(Calendar.WEEK_OF_YEAR)==cal.get(Calendar.WEEK_OF_YEAR)) {
				week.add(e);
			}
		}
		return week;
	}
	
	public static void deleteEvent(Event e){
		System.out.println(database.toString());
		remove(e);
		System.out.println(database.toString());
	}
}