package scheduler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerListModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

public class EventMaker {
	
	private JFrame frame;
	private JPanel mainPanel;
	private JPanel chooseDatePanel;
	private JPanel centerChooseDatePanel;
	
	private JLabel chooseDate;
	
	private JSpinner monthSpinner;
	private JSpinner daySpinner;
	private JSpinner yearSpinner;
	
	private int month;
	private int day;
	private int year;
	
	public EventMaker(){
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(600, 350));
        final JTextField descript;
        final JTextField type;
        final JTextField eventDate;
        final JTextField startTime;
        final JTextField endTime;

        frame.setTitle("Create an Event");
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));

        panel.add(new JLabel("Event Description: "));
        descript = new JTextField(30);
        panel.add(descript);
        
        panel.add(new JLabel("Event Type (class, work, or other?): "));
        type = new JTextField(30);
        panel.add(type);

        panel.add(new JLabel("Event Date (MM/DD/YYYY):  "));
        eventDate = new JTextField(10);
        panel.add(eventDate);

        panel.add(new JLabel("Start Time (0-23 hours): "));
        startTime = new JTextField(5);
        panel.add(startTime);

        panel.add(new JLabel("Duration (in terms of minutes): "));
        endTime = new JTextField(5);
        panel.add(endTime);

        panel.add(new JLabel());
        JButton doneBtn = new JButton("Create Event");
        
        panel.add(doneBtn);
        doneBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String[] date = eventDate.getText().split("/");
				int day = Integer.parseInt(date[1]);
				int month = Integer.parseInt(date[0]);
				int year = Integer.parseInt(date[2]);
				int hour = Integer.parseInt(startTime.getText());
				int duration = Integer.parseInt(endTime.getText());
				int typeNum;
				if(type.getText().equals("class")){
					typeNum = 0;
				}
				else if(type.getText().equals("work")){
					typeNum = 1;
				}
				else{
					typeNum = 2;
				}
			
				Date time = new Date(year, month, day);
				
				Event newEvent = new Event(time, duration, typeNum, descript.getText());
				
				System.out.println(newEvent.getDate());
				System.out.println(newEvent.getHour());
				System.out.println(newEvent.getMonth());
				System.out.println(newEvent.getType());
				System.out.println(newEvent.toString());
			}
		});
        

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

	}
}
