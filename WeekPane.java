package scheduler;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class WeekPane extends JScrollPane{
	private static final int WIDTH = 800;
	private static final int HEIGHT = 500;
	ArrayList<Event> events;
	JLayeredPane base;
	
	public WeekPane(ArrayList<Event> events) {
		this.events = events;
		base = makeWeek();
		this.setViewportView(base);
		this.setSize(WIDTH,HEIGHT);
		this.setLocation(100,0);
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
		}
		base.setVisible(true);
		base.setOpaque(true);
		return base;
	}
}
