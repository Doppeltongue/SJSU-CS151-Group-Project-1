package scheduler;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class EventMaker {
	
	private JFrame frame;
	private JPanel mainPanel;
	public EventMaker(){
		frame = new JFrame();
		frame.setPreferredSize(new Dimension(600, 350));
		mainPanel = new JPanel(new BorderLayout(2,2));
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

        panel.add(new JLabel("Start Time (HH:MM, 0 - 23 hours): "));
        startTime = new JTextField(5);
        panel.add(startTime);

        panel.add(new JLabel("Duration (in terms of minutes): "));
        endTime = new JTextField(5);
        panel.add(endTime);

        panel.add(new JLabel());
        JButton doneBtn = new JButton("Create Event");
        
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(doneBtn);
        mainPanel.add(panel, BorderLayout.CENTER);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        doneBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[] date = eventDate.getText().split("/");
				String[] timeSplit = startTime.getText().split(":");
				
				
				int day = Integer.parseInt(date[1]);
				int month = Integer.parseInt(date[0]);
				int year = Integer.parseInt(date[2]);
				int hour = Integer.parseInt(timeSplit[0]);
				int min = Integer.parseInt(timeSplit[1]);
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
			
			
				GregorianCalendar cal = new GregorianCalendar(year, month - 1, day, hour, min);
				Date time  = cal.getTime();
				
				Event newEvent = new Event(time, duration, typeNum, descript.getText());
				
				System.out.println(month);
				System.out.println(year);
				System.out.println(newEvent.getDate());
				System.out.println(newEvent.getHour());
				System.out.println(newEvent.getMonth());
				System.out.println(newEvent.getType());
				System.out.println(newEvent.toString());
				System.out.println(duration);
				
				ScheduleController.addEvent(newEvent);
				frame.dispose();
			}
		});
        

        frame.add(mainPanel);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
        
     public void show() {   
        frame.pack();
        frame.setVisible(true);

	}
}
