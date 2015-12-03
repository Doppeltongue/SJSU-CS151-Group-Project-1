package scheduler;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

public class EventView {
	private static final int HEIGHT = 320;
	private static final int WIDTH = 300;
	
	JFrame frame;
	Event event;
	
	public EventView(Event event) {
		this.event = event;
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.setLayout(null);
		frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		populate();
	}
	
	private void populate() {
		JLabel date = new JLabel("Date: " + event.getDate()+ "  Time: "+ event.getTime());
		date.setHorizontalAlignment(SwingConstants.LEFT);
		JPanel dateBox = new JPanel();
		dateBox.setLayout(new FlowLayout(FlowLayout.LEFT));
		dateBox.add(date);
		dateBox.setOpaque(true);
		dateBox.setLocation(10,10);
		dateBox.setSize(new Dimension(200,20));
		dateBox.repaint();
		
		JLabel duration = new JLabel("Duration: " + event.getDuration()+ " minutes");
		duration.setHorizontalAlignment(SwingConstants.LEFT);
		JPanel durationBox = new JPanel();
		durationBox.setLayout(new FlowLayout(FlowLayout.LEFT));
		durationBox.add(duration);
		durationBox.setOpaque(true);
		durationBox.setLocation(10,40);
		durationBox.setSize(new Dimension(200, 20));
		durationBox.repaint();
		
		JTextPane description = new JTextPane();
		description.setText(event.getDescription());
		description.setLocation(10,80);
		description.setSize(265,140);
		description.setEditable(false);
		description.setBackground(frame.getBackground());
		
		JPanel deletePane = new JPanel();
		deletePane.setSize(100,60);
		deletePane.setLocation(90,230);
		
		JButton delete = new JButton("Delete");
		delete.setText("Delete");
		delete.setPreferredSize(new Dimension(80,30));
		delete.addActionListener(delete());
		deletePane.add(delete);
		
		frame.add(dateBox);
		frame.add(durationBox);
		frame.add(description);
		frame.add(deletePane);
	}
	
	public void show() {
		frame.pack();
		frame.setVisible(true);
	}
	
	public ActionListener delete() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				ScheduleController.deleteEvent(event);
				frame.setVisible(false);
				frame.dispose();
				
			}
			
		};
		
	}
}
