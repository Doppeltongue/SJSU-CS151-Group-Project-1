package scheduler;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.JLayeredPane;
import javax.swing.JScrollPane;

public class WeekPane extends JScrollPane{
	private static final int WIDTH = 820;
	private static final int HEIGHT = 500;
	ArrayList<Event> events;
	JLayeredPane base;
	
	public WeekPane(ArrayList<Event> events) {
		this.events = events;
		base = makeWeek();
		this.setViewportView(base);
		this.setSize(WIDTH,HEIGHT);
		this.setLocation(48,110);
		this.setVisible(true);
	}
	
	private JLayeredPane makeWeek() {
		JLayeredPane base = new JLayeredPane();
		base.setLayout(null);
		base.setPreferredSize(new Dimension(800, 32*24));
		for (int i=0;i<24;i++) {
			base.add(new HourPanel(i));
		}
		for (Event e: events) {
			EventButton butt = new EventButton(e);
			base.add(butt);
			base.moveToFront(butt);
			if (e.getEndDay()!=e.getDay()){
				EventButton ton = new EventButton(e);
				ton.setLocation(butt.getX()+100,0);
				Calendar cal = Calendar.getInstance();
				cal.setTime(e.getEndTime());
				ton.setSize(100, 32*(cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)/60));
				base.add(ton);
				base.moveToFront(ton);
			}
		}
		base.setVisible(true);
		base.setOpaque(true);
		return base;
	}
}
