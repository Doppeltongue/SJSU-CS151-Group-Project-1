package scheduler;

import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class HeaderPanel extends JPanel {

	private String startDateLabel = "";
	private String endDateLabel = "";
	private final JButton prevButton;
	private final JButton nextButton;
	private String weekLabel = "";
	private JLabel dates;
	private Calendar startDate;
	private Calendar endDate;
	private JPanel daysOfWeek;
	
	private int currentDayOfWeek;
	
	
	
	public HeaderPanel(Calendar currentDate) {
		
		currentDayOfWeek = currentDate.get(Calendar.DAY_OF_WEEK);
		startDate = getStartDate();
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
		startDate = this.getStartDate();
		endDate = this.getEndDate();
		startDateLabel = getStartDateLabel(currentDate);
		endDateLabel = getEndDateLabel(currentDate);
		weekLabel = this.getWeekLabel();
		dates = new JLabel(weekLabel);
		addPanels();
		
		
		
		
	}
	
	/*
	 * Returns the start date of the Week
	 */
	
	public Calendar getStartDate(){
		Calendar tempDay = Calendar.getInstance();
		if(currentDayOfWeek != 1){
			Boolean isSunday = false;
			while(!isSunday){
				tempDay.add(Calendar.DATE, -1);
				if((tempDay.get(Calendar.DAY_OF_WEEK)) == 1){
					startDate = tempDay;
					isSunday = true;
				}
			}
		}
		
		else{
			startDate = tempDay;
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
				// TODO Auto-generated method stub
				startDate.add(Calendar.DATE, (7*num));
				startDateLabel = getStartDateLabel(startDate);
				endDate = getEndDate();
				endDateLabel = getEndDateLabel(endDate);
				weekLabel = getWeekLabel();
				dates.setText(weekLabel);
				removeAll();
				addPanels();
				
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
	
	public void daysOfWeekSetUp(JPanel panel){
		String[] daysOfWeek = new String[] {"Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"};
		for(String e: daysOfWeek){
			JLabel day = new JLabel(e);
			day.setFont(new Font("Serif", Font.PLAIN, 25));
			panel.add(day);
		}
	}
	
}
