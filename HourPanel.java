package scheduler;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HourPanel extends JPanel{
	private static final int HEIGHT = 32;
	private static final int WIDTH = 800;
	private static final Color ALTCOLOR = new Color(230,230,255);
	private int hour;
	
	public HourPanel(int hour) {
		this.hour=hour;
		generate();
	}
	
	private void generate() {
		this.setSize(WIDTH,HEIGHT);
		this.setLocation(0, HEIGHT*hour);
		if (hour%2==0) {
			this.setBackground(Color.WHITE);
		}
		else this.setBackground(ALTCOLOR);
		JLabel label = new JLabel(hour+":00");
		this.setLayout(new FlowLayout(FlowLayout.LEFT));
		this.add(label);
		//this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}
	
	
}
