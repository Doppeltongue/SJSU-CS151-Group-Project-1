package scheduler;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;

import javax.swing.JButton;

public class EventButton extends JButton{
	private static final int WIDTH = 100;
	private static final int HEIGHT_MODIFIER = 32;
	private static final Color CLASS_COLOR = new Color(135,206,235);
	private static final Color WORK_COLOR = new Color(255,160,122);
	private static final Color OTHER_COLOR = new Color(192,255,62);
	private static final Color[] COLORS = {CLASS_COLOR,WORK_COLOR,OTHER_COLOR};
	
	Event event;
	
	public EventButton(Event event) {
		this.event = event;
		event.getStartTime();
		this.setLocation(WIDTH*(event.getCalendar().get(Calendar.DAY_OF_WEEK)), HEIGHT_MODIFIER*event.getHour());
		this.setText(event.getTypeName());
		this.setBackground(COLORS[event.getType()]);
		this.setSize(WIDTH,HEIGHT_MODIFIER*event.getDuration());
		this.setOpaque(true);
		this.addActionListener(press());
	}

	private ActionListener press() {
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				EventView view = new EventView(event);
				view.show();
			}
			
		};
	}
}
