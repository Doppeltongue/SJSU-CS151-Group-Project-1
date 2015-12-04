package scheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class WeekPane extends JScrollPane{
	private static final int WIDTH = 820;
	private static final int HEIGHT = 500;
	ArrayList<Event> events;
	JLayeredPane base;
	JPanel line;
	Timer timer;
	
	public WeekPane(ArrayList<Event> events) {
		timer = new Timer(30*1000,update());
		timer.start();
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
		base.setPreferredSize(new Dimension(800, 30*24));
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
				ton.setSize(100, 30*cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)/2);
				base.add(ton);
				base.moveToFront(ton);
			}
		}
		base.setVisible(true);
		base.setOpaque(true);
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal2.setTime(ScheduleController.date);
		if (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) &&
                  cal1.get(Calendar.WEEK_OF_YEAR) == cal2.get(Calendar.WEEK_OF_YEAR)) {
			line = new JPanel();
			updateLine();
			base.add(line);
			base.moveToFront(line);
		}
		return base;
	}
	
	private void updateLine() {
		Calendar cal = Calendar.getInstance();
		int x = cal.get(Calendar.DAY_OF_WEEK)*100;
		int y = 30*cal.get(Calendar.HOUR_OF_DAY)+cal.get(Calendar.MINUTE)/2;
		int width = 100;
		int height = 2;
		line.setBounds(x, y, width, height);
		line.setBorder(BorderFactory.createLineBorder(Color.RED));
	}
	
	private ActionListener update() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				updateLine();
				repaint();
				ScheduleController.alert();
			}
			
		};
	}
}
