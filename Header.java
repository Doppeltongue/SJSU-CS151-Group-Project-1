package scheduler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Header extends JPanel{
	private Date date;
	
	public Header(Date date) {
		this.date=date;
		populate();
	}

	public void populate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		HeaderPanel datePanel = new HeaderPanel(cal);
		datePanel.setVisible(true);
		this.setVisible(true);
		this.setLayout(new BorderLayout(2,2));
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
		addEvent.addActionListener(addButton());
		header.add(addEvent, BorderLayout.EAST);
		this.add(header, BorderLayout.NORTH);
		this.add(datePanel.getDayBar(), BorderLayout.SOUTH);
		
		
		this.setOpaque(true);
		this.setLocation(0,0);
		//this.setPreferredSize(new Dimension(1000,200));
		this.setMaximumSize(new Dimension(900,110));
		this.setSize(900,110);
		this.repaint();
	}
	
	private ActionListener addButton() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventMaker maker = new EventMaker();
				maker.show();
			}
			
		};
	}
	
}
