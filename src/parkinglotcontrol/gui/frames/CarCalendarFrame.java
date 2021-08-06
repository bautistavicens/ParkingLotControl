package parkinglotcontrol.gui.frames;


import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import parkinglotcontrol.models.CustomCalendar;


public class CarCalendarFrame extends JFrame implements PropertyChangeListener {

	private static final long serialVersionUID = 1L;
	private CarUploadFrame CUFrame;
	private CustomCalendar calendarWindow;
	private String calendarType;
	//the TextField for typing the date
	private JFormattedTextField  textField = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
	
		
		public CarCalendarFrame(CarUploadFrame CUFrame, String calendarType) {

			setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
			setSize(368, 362);
			setTitle("Parking Lot Control");
			setLocationRelativeTo(null);
			
			this.CUFrame = CUFrame;
			this.calendarType = calendarType;
			
			Container cp = getContentPane();
			FlowLayout flowLayout = new FlowLayout();
			
			cp.setLayout(flowLayout);			
			 			
			 
			textField.setValue(new Date());
			textField.setPreferredSize(new Dimension(130, 30));
			    
			// display the window with the calendar
			calendarWindow = new CustomCalendar(); 
			    
			//wire a listener for the PropertyChange event of the calendar window
			calendarWindow.addPropertyChangeListener(this);
			
			
			JButton calendarButton = new JButton();
			calendarButton.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/arrow_down_icon_small.png")));
			calendarButton.setContentAreaFilled(false);
			calendarButton.setBorderPainted(false);
					
			calendarButton.addActionListener(new ActionListener()
			{
			  public void actionPerformed(ActionEvent e)
			  {
				//render the calendar window below the text field
				calendarWindow.setLocation(textField.getLocationOnScreen().x, (textField.getLocationOnScreen().y + textField.getHeight()));
				//get the Date and assign it to the calendar
				Date d = (Date)textField.getValue();				
					
				calendarWindow.resetSelection(d);				

			    calendarWindow.setVisible(true);
			  }
			});
			
			JButton okButton = new JButton();
			okButton.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/green_check_icon.png")));
			okButton.setContentAreaFilled(false);
			okButton.setBorderPainted(false);
			
			okButton.addActionListener(ActionEvent -> {
				handleClosing();
			});

			//add the UI controls to the ContentPane
			cp.add(textField);
			cp.add(calendarButton);
			cp.add(okButton);
			cp.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			
			this.addWindowListener(new WindowAdapter() {
			    public void windowClosing(WindowEvent e) {
			        handleClosing();
			    }
			});
	        pack();
		}
		
		//Use this to get the Date.
        public JFormattedTextField getTextField() {
			return textField;
		}

		public void setTextField(JFormattedTextField textField) {
			this.textField = textField;
		}

		@Override
		public void propertyChange(PropertyChangeEvent event) {
			
        	//get the selected date from the calendar control and set it to the text field
			if (event.getPropertyName().equals("selectedDate")) {
	            
				java.util.Calendar cal = (java.util.Calendar)event.getNewValue();
				Date selDate =  cal.getTime();
				textField.setValue(selDate);
				
				//
				if(calendarType.equals("IN")) {
	    			CUFrame.getTextPaneIn().setValue(textField.getValue());
	        	}
	    		else if(calendarType.equals("OUT")) {
	    			CUFrame.getTextPaneOut().setValue(textField.getValue());
	    		}
	    		else {
	    			JOptionPane.showMessageDialog(null, "No se ha podido colocar la fecha", "Error", JOptionPane.ERROR_MESSAGE);
	    		}
	        }
			
		}
        
        public void handleClosing() {
        	calendarWindow.dispose();
        	this.dispose();
        }

}
