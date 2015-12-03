package scheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.WindowConstants;


public class MyTester {
	public static void main(String args[]) {
		bigTest();
		//headerTest();
	}
	
	public static void test1() {
		Date current = new Date();
		Event e = new Event(current, 0, 1,"");
		System.out.println(e.getDay());
		
		GregorianCalendar stuff = new GregorianCalendar();
		System.out.println(stuff.toString());
		Calendar today = Calendar.getInstance();
		System.out.println(today.toString());
		
		ScheduleController thing = new ScheduleController();
		//thing.add(e);
		//thing.load();
		ArrayList<Event> fun = thing.getDay(current);
		System.out.println(fun.toString());
		
		System.out.println((current.getMonth()+1)+"/"+current.getDate()+"/"+(current.getYear()+1900));
		
		
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        frame.setPreferredSize(new Dimension(1000,1000));
        
        JLayeredPane p1 = new JLayeredPane();
        p1.setSize(200, 200);
        p1.setLocation(100, 100);
        p1.setBackground(Color.GREEN);
        p1.setOpaque(true);
        p1.setVisible(true);
        p1.setLayout(null);
        JPanel p2 = new JPanel();
        p2.setSize(100, 100);
        //p2.setPreferredSize(new Dimension(100,100));
        p2.setBounds(25, 50, 100, 100);
        //p2.setLocation(125,150);
        p2.setBackground(Color.BLUE);
        p2.setVisible(true);
        
        JPanel p4 = new JPanel();
        p4.setBackground(Color.RED);
        p4.setSize(50, 50);
        p4.setLocation(40, 40);
        p2.repaint();
        p4.repaint();
        p4.setVisible(true);
        p1.add(p2);
        p1.add(p4);
        p1.moveToFront(p4);
        
        JScrollPane pane = new JScrollPane(p1);
        pane.setSize(100,100);
        //pane.setMaximumSize(new Dimension(100,100));
        pane.setLocation(100,100);
        pane.repaint();
        p1.setPreferredSize(new Dimension(200,200));
        p1.repaint();
        
        JPanel p3 = new JPanel();
        //p3.setBackground(Color.BLACK);
        p3.setLayout(null);
        p3.setSize(new Dimension(500,500));
        p3.setLocation(00, 00);
        p3.add(pane);
        p3.setOpaque(true);
        p3.repaint();
        
        //frame.add(p1);
        frame.add(p3);
        //frame.add(pane);
        //frame.add(p3);
        //frame.add(view);
		frame.pack();
		frame.setVisible(true);
		
	}

	public static void eventViewTest() {
		ScheduleController control = new ScheduleController();
		Date date = new Date();
		for (int i=0;i<10;i++) {
			int hour = i*2;
			date.setHours(hour);
			Event now = new Event(date, 1, 1, "hawiehuoawh sisadbfuo iqwebuoabsdiubfoi wuqbeuiobui obaiousdbgoq");
			control.add(now);
		}
		date.setDate(3);
		control.add(new Event(date, 3, 0, "aawefhaoweufhaouiwehf"));
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setLayout(null);
		frame.setPreferredSize(new Dimension(1000, 1000));
		
		WeekPane pane = new WeekPane(control.getWeek(new Date()));
        
        /**
        JPanel p3 = new JPanel();
        //p3.setBackground(Color.BLACK);
        p3.setLayout(null);
        p3.setSize(new Dimension(800,500));
        p3.setLocation(00, 00);
        p3.add(pane);
        p3.setOpaque(true);
        p3.repaint();
		*/
        
		frame.add(pane);
		//frame.add(butt);
		frame.pack();
		frame.setVisible(true);
	}

	public static void bigTest() {
		ScheduleController control = new ScheduleController();
		
		/**
		Date date = new Date();
		for (int i=0;i<10;i++) {
			int hour = i*2;
			date.setHours(hour);
			Event now = new Event(date, 1, 1, "hawiehuoawh sisadbfuo iqwebuoabsdiubfoi wuqbeuiobui obaiousdbgoq");
			control.add(now);
		}
		date.setDate(3);
		control.add(new Event(date, 3, 0, "aawefhaoweufhaouiwehf"));
		// */
		
		
		
		control.show();
	}
	
	public static void headerTest() {
		Date date = new Date();
		JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setBackground(Color.WHITE);
        frame.setPreferredSize(new Dimension(1000,1000));
        frame.add(new Header(date));
        frame.pack();
        frame.setVisible(true);
	}
}
