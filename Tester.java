package scheduler;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.util.Calendar;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tester {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JFrame frame = new JFrame("SUSCAP");
		JPanel mainPanel = new JPanel();
		Calendar test = Calendar.getInstance();
		HeaderPanel datePanel = new HeaderPanel(test);
		datePanel.setVisible(true);
		mainPanel.setVisible(true);
		mainPanel.setLayout(new BorderLayout(2,2));
		JPanel header = new JPanel(new BorderLayout(2,2));
		header.setVisible(true);
		JPanel centerPan = new JPanel();
		centerPan.setLayout(new BorderLayout(2,2));
		JLabel weekOf = new JLabel("Week Of: ");
		weekOf.setFont(new Font("Serif", Font.PLAIN, 30));
		weekOf.setHorizontalAlignment(JLabel.CENTER);
		centerPan.add(weekOf, BorderLayout.NORTH);
		centerPan.add(datePanel, BorderLayout.CENTER);
		header.add(centerPan, BorderLayout.CENTER);
		JButton addEvent = new JButton("+");
		addEvent.setFont(new Font("Serif", Font.PLAIN, 30));
		header.add(addEvent, BorderLayout.EAST);
	
		mainPanel.add(header, BorderLayout.NORTH);
		
		
		
		frame.add(mainPanel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setSize(800, 500);

	}
}
