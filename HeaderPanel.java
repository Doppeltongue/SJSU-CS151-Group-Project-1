package scheduler;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {
	private static final String[] DAYS = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};

	private String startDateLabel = "";
	private String endDateLabel = "";
	private final JButton prevButton;
	private final JButton nextButton;
	private String weekLabel = "";
	private JLabel dates;
	private Calendar startDate;
	private Calendar endDate;
	
	private int currentDayOfWeek;
	
	
	
	public HeaderPanel(Calendar cal) {
		
		currentDayOfWeek = cal.get(Calendar.DAY_OF_WEEK);
		startDate = getStartDate(cal);
		endDate = getEndDate();
		startDateLabel = getStartDateLabel(startDate);
		endDateLabel = getEndDateLabel(endDate);
		weekLabel = getWeekLabel();
		dates = new JLabel(weekLabel);
		dates.setFont(new Font("Serif", Font.PLAIN, 30));
		prevButton = new JButton("<");
		prevButton.addActionListener(changeWeek(-1));
		dates = new JLabel(weekLabel);
		nextButton = new JButton(">");
		nextButton.addActionListener(changeWeek(1));
		startDateLabel = getStartDateLabel(cal);
		endDateLabel = getEndDateLabel(cal);
		weekLabel = this.getWeekLabel();
		dates = new JLabel(weekLabel);
		addPanels();
	}
	
	/*
	 * Returns the start date of the Week
	 */
	
	public Calendar getStartDate(Calendar cal){
		if(currentDayOfWeek != 1){
			Boolean isSunday = false;
			while(!isSunday){
				cal.add(Calendar.DATE, -1);
				if((cal.get(Calendar.DAY_OF_WEEK)) == 1){
					startDate = cal;
					isSunday = true;
				}
			}
		}
		
		else{
			startDate = cal;
		}
		
		return startDate;
	}
	
	/*
	 * Returns the start date of the week as a string
	 */
	public String getStartDateLabel(Calendar cal) {
		String answer = ((startDate.get(Calendar.MONTH) + 1) + "/" + startDate.get(Calendar.DATE) + "/" + startDate.get(Calendar.YEAR));
		
		return answer;
	}
	
	/*
	 * Returns the end date of the Week
	 */
	
	public Calendar getEndDate(){
		Calendar temp = Calendar.getInstance();
		temp.setTime(startDate.getTime());
		temp.add(Calendar.DATE, 6);
		
		return temp;
	}
	
	/*
	 * Returns the start date of the week as a string
	 */
	public String getEndDateLabel(Calendar today) {
		String answer = ((endDate.get(Calendar.MONTH) + 1) + "/" + endDate.get(Calendar.DATE) + "/" + endDate.get(Calendar.YEAR));
		return answer;
	}
	
		
	/*
	 * ActionListener for prevButton and nextButton.
	 * 
	 */
	
	public ActionListener changeWeek(final int num) {
		return new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				startDate.add(Calendar.DATE, (7*num));
				ScheduleController.setDate(startDate.getTime());
				ScheduleController.refresh();
				/**
				startDateLabel = getStartDateLabel(startDate);
				endDate = getEndDate();
				endDateLabel = getEndDateLabel(endDate);
				weekLabel = getWeekLabel();
				dates.setText(weekLabel);
				removeAll();
				addPanels();
				*/
				
			}
		};
		
	}
	
	/*
	 * Repaints all components
	 */
	public void addPanels(){
		this.add(prevButton);
		this.add(dates);
		this.add(nextButton);

	}
	
	/*
	 * Formula for weekLabel 
	 */
	public String getWeekLabel(){
		return (startDateLabel + " " + "-" + " " + endDateLabel);
	}
	
	
	/*
	 * sets up days of week and adds them to a panel
	 */
	
	public JPanel getDayBar(){
		Dimension dimension = new Dimension(100,30);
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(startDate.getTime());
		JPanel spacer1 = new JPanel();
		spacer1.setPreferredSize(dimension);
		panel.add(spacer1);
		JPanel filler = new JPanel();
		filler.setMaximumSize(dimension);
		filler.setMinimumSize(dimension);
		filler.setPreferredSize(dimension);
		String str = new String("Time");
		JLabel label = new JLabel(str);
		label.setFont(new Font("Serif", Font.PLAIN, 20));
		filler.add(label);
		filler.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		filler.setBackground(new Color(255,131,250));
		panel.add(filler);
		for (int i=0;i<7;i++) {
			JPanel day = new JPanel();
			day.setMaximumSize(dimension);
			day.setMinimumSize(dimension);
			day.setPreferredSize(dimension);
			ArrayList<Event> events = ScheduleController.getDay(calendar.getTime());
			if (events.isEmpty()) {day.setBackground(new Color(152,251,152));}
			else day.setBackground(new Color(255,228,225));
			str = new String(DAYS[i]+" " + (calendar.get(Calendar.MONTH)+1)+"/"+calendar.get(Calendar.DATE));
			label = new JLabel(str);
			label.setFont(new Font("Serif", Font.PLAIN, 20));
			day.add(label);
			day.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			panel.add(day);
			calendar.add(Calendar.DATE, 1);
		}
		JPanel spacer2 = new JPanel();
		spacer2.setPreferredSize(dimension);
		panel.add(spacer2);
		return panel;
	}
	
}
