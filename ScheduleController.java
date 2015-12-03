package scheduler;

import java.awt.Dimension;
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
import javax.swing.WindowConstants;



/**
 * 
 * @author Phillip, Ricardo, Tri
 * 
 * Database of the Calendar implemented with an ArrayList
 * All methods that alter the database will automatically save any changes
 */
public class ScheduleController {
	public static final int BUTTON_WIDTH = 100;
	private static final String SAVE_LOCATION = new String("schedule.txt");
	static ArrayList<Event> database; //ArrayList is used since we don't know what the database size will be
	static JFrame foundation;
	static WeekPane pane;
	public static Date date;
	
	public ScheduleController() {
		database = new ArrayList<Event>();
		date = new Date();
		foundation = new JFrame("SUSCAP");
		foundation.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		foundation.setLayout(null);
		foundation.setPreferredSize(new Dimension(916, 675));
		startUp();
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
	
	public static void add(Event e) {
		database.add(e);
		save();
		refresh();
	}
	
	public static void remove(Event e) {
		database.remove(e);
		save();
	}
	
	public static ArrayList<Event> getDay(Date thedate) {
		ArrayList<Event> day = new ArrayList<Event>();
		for (Event e: database) {
			Calendar cal1 = Calendar.getInstance();
			Calendar cal2 = Calendar.getInstance();
			cal1.setTime(e.getStartTime());
			cal2.setTime(thedate);
			if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
	                  cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)) {
				day.add(e);
			}
		}
		return day;
	}
	
	public static ArrayList<Event> getWeek(Date date) {
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
	
	public void startUp() {
		load();
		fill();
	}
	
	public static void fill() {

		foundation.add(new Header(date));
		foundation.add(new WeekPane(getWeek(date)));
	}
	
	public void show() {
		foundation.pack();
		foundation.setVisible(true);
	}
	
	public static void deleteEvent(Event e){
		remove(e);
		refresh();
		
	}
	
	public static void addEvent(Event e) {
		add(e);
	}
	
	public static void refresh() {
		foundation.getContentPane().removeAll();
		fill();
		foundation.validate();
		foundation.repaint();
	}
	
	public static void setDate(Date d) {
		date = d;
	}
}
