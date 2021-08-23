package parkinglotcontrol.gui.frames;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.ConcurrentModificationException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.enums.CarBrands;
import parkinglotcontrol.interfaces.GuiUploadMethod;
import parkinglotcontrol.models.Car;
import parkinglotcontrol.models.ParkingLot;

public class CarUploadFrame extends JFrame implements GuiUploadMethod {

	private static final long serialVersionUID = -294565520020423779L;
	private JPanel contentPane;
	private String[] floors = {"PB", "1", "2", "3", "4", "5", "6", "7", "8"};
	private DefaultComboBoxModel<String> carBrandsComboBoxModel;
	private DefaultComboBoxModel<Integer> parkingLotsNumbersComboBoxModel;
	private JComboBox<String> comboBoxBrands;
	private JComboBox<String> comboBoxFloor;
	private JComboBox<Integer> comboBoxParkingLotsNumbers;
	private JTextField textFieldlLicencePlate;
	private JTextField textFieldOwner;
	private JFormattedTextField textPaneIn;
	private JFormattedTextField textPaneOut;
	private JSpinner spinnerHourDayIn;
	private JSpinner spinnerHourDayOut;
	private CarCalendarFrame calendar;
	private String calendarType;

	public CarUploadFrame() {
		
		this.setSize(500, 500);
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setIconImage(new ImageIcon(getClass().getResource("/parkinglotcontrol/images/logo/program_icon.png")).getImage());
		
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initJComboBoxes();
		initLabels();
		initTextFields();
		initButtons();
		initSpinners();
		getParkingLotsNumbers();
		
		try {
			//This property indicates that we'll use the calendar for the day of entry.
			calendar = new CarCalendarFrame(this, null);
			calendar.setTitle("-");
			calendar.setVisible(false);
			
		}catch (Exception exp) {
			JOptionPane.showMessageDialog(null, "No se ha podido inicializar el calendario.", "!", JOptionPane.WARNING_MESSAGE);
		}
	}
	

	public JFormattedTextField getTextPaneIn() {
		return textPaneIn;
	}

	public void setTextPaneIn(JFormattedTextField textPaneIn) {
		this.textPaneIn = textPaneIn;
	}

	public JFormattedTextField getTextPaneOut() {
		return textPaneOut;
	}

	public void setTextPaneOut(JFormattedTextField textPaneOut) {
		this.textPaneOut = textPaneOut;
	}
	

	public void initLabels() {
		JLabel lblTitle = new JLabel("Registro de auto");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblTitle.setBounds(146, 10, 198, 46);
		contentPane.add(lblTitle);
		
		JLabel lblLicencePlate = new JLabel("Patente");
		lblLicencePlate.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblLicencePlate.setBounds(10, 102, 67, 35);
		contentPane.add(lblLicencePlate);
		
		JLabel lblOwner = new JLabel("Due\u00F1o");
		lblOwner.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblOwner.setBounds(10, 147, 67, 35);
		contentPane.add(lblOwner);
		
		JLabel lblBrand = new JLabel("Marca");
		lblBrand.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblBrand.setBounds(10, 192, 67, 35);
		contentPane.add(lblBrand);
		
		JLabel lblFloor = new JLabel("Piso");
		lblFloor.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFloor.setBounds(10, 237, 67, 35);
		contentPane.add(lblFloor);
		
		JLabel lblId = new JLabel("N\u00FAmero");
		lblId.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblId.setBounds(10, 282, 67, 35);
		contentPane.add(lblId);
		
			
	}
	
	
	public void initTextFields() {

		textFieldlLicencePlate = new JTextField();
		textFieldlLicencePlate.setBounds(87, 105, 153, 36);
		contentPane.add(textFieldlLicencePlate);
		textFieldlLicencePlate.setColumns(10);
		
		textFieldOwner = new JTextField();
		textFieldOwner.setColumns(10);
		textFieldOwner.setBounds(87, 146, 153, 36);
		contentPane.add(textFieldOwner);
		
		textPaneIn = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		textPaneIn.setBounds(328, 106, 97, 35);
		textPaneIn.setValue(new Date());
		contentPane.add(textPaneIn);
		
		textPaneOut = new JFormattedTextField(DateFormat.getDateInstance(DateFormat.SHORT));
		textPaneOut.setBounds(328, 214, 97, 35);
		textPaneOut.setValue(new Date());
		contentPane.add(textPaneOut);
		
	}
	
	
	public void initJComboBoxes() {
		
		carBrandsComboBoxModel = new DefaultComboBoxModel<String>();
		parkingLotsNumbersComboBoxModel = new DefaultComboBoxModel<Integer>();
		
		comboBoxBrands = new JComboBox<String>(carBrandsComboBoxModel);
		comboBoxBrands.setBounds(87, 195, 153, 35);
		
		comboBoxFloor = new JComboBox<String>(new DefaultComboBoxModel<String>(floors));
		comboBoxFloor.setBounds(87, 238, 53, 38);
		
		comboBoxParkingLotsNumbers = new JComboBox<Integer>(parkingLotsNumbersComboBoxModel);
		comboBoxParkingLotsNumbers.setBounds(87, 286, 53, 35);
		
		for(CarBrands cb : CarBrands.values()) {
			carBrandsComboBoxModel.addElement(cb.brandTitle);
		}
		
		comboBoxFloor.addActionListener(ActionEvent -> {
			getParkingLotsNumbers();
		});
		
		contentPane.add(comboBoxBrands);
		contentPane.add(comboBoxFloor);
		contentPane.add(comboBoxParkingLotsNumbers);
		
	}
	
	public void initButtons() {
		
		JButton btnOk = new JButton("Aceptar");
		btnOk.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/green_check_icon.png")));
		btnOk.setBounds(146, 374, 85, 57);
		btnOk.setContentAreaFilled(false);
		btnOk.setBorderPainted(false);
		btnOk.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnOk.setHorizontalTextPosition(SwingConstants.CENTER);
		btnOk.addActionListener((ActionEvent e)-> {
		     int n = JOptionPane.showConfirmDialog(null,"¿Cargar datos?" ,"!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		     if(n == JOptionPane.OK_OPTION) {
		    	
		    	this.upload();
		  	 
		     }
		});
		contentPane.add(btnOk);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/red_cancel_icon.png")));
		btnCancel.setBounds(259, 374, 85, 57);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnCancel.addActionListener((ActionEvent e)-> {
			int n = JOptionPane.showConfirmDialog(null,"¿Cancelar operación?" ,"!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		    if(n == JOptionPane.OK_OPTION) {
		    	
		    	this.calendar.dispose();
		    	this.dispose();
		    }
		});
		
		contentPane.add(btnCancel);
		
		JButton btnCalendarIn = new JButton("Desde");
		btnCalendarIn.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		btnCalendarIn.setBounds(259, 102, 65, 50);
		btnCalendarIn.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/calendar_icon_small.png")));
		btnCalendarIn.setContentAreaFilled(false);
		btnCalendarIn.setBorderPainted(false);
		btnCalendarIn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCalendarIn.setHorizontalTextPosition(SwingConstants.CENTER);
		
		contentPane.add(btnCalendarIn);
		
		btnCalendarIn.addActionListener(ActionEvent -> {
			try {
				//This property indicates that we'll use the calendar for the day of entry.
				calendarType = "IN";
				calendar = new CarCalendarFrame(this, calendarType);
				calendar.setTitle("Entrada");
				calendar.setVisible(true);
				
			}catch (Exception exp) {
				JOptionPane.showMessageDialog(null, "No se ha podido inicializar el calendario.", "!", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		JButton btnCalendarOut = new JButton("Hasta");
		btnCalendarOut.setFont(new Font("Tahoma", Font.PLAIN, 9));
		
		btnCalendarOut.setBounds(259, 214, 65, 50);
		btnCalendarOut.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/calendar_icon_small.png")));
		btnCalendarOut.setContentAreaFilled(false);
		btnCalendarOut.setBorderPainted(false);
		btnCalendarOut.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCalendarOut.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnCalendarOut.addActionListener(ActionEvent -> {
			try {
				//This property indicates that we'll use the calendar for the day of leaving.
				calendarType = "OUT";
				calendar = new CarCalendarFrame(this, calendarType);
				calendar.setTitle("Salida");
				calendar.setVisible(true);
				
			}catch (Exception exp) {
				JOptionPane.showMessageDialog(null, "No se ha podido inicializar el calendario.", "!", JOptionPane.WARNING_MESSAGE);
			}
		});
		
		contentPane.add(btnCalendarOut);
		
	}
	
	public void initSpinners() {
		spinnerHourDayIn = new JSpinner();
		spinnerHourDayIn.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinnerHourDayIn.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor spinnerDE1 = new JSpinner.DateEditor(spinnerHourDayIn, "HH:mm");
		spinnerHourDayIn.setEditor(spinnerDE1);
		spinnerHourDayIn.setBounds(328, 147, 97, 35);
		contentPane.add(spinnerHourDayIn);
		
		spinnerHourDayOut = new JSpinner();
		spinnerHourDayOut.setFont(new Font("Tahoma", Font.PLAIN, 12));
		spinnerHourDayOut.setModel(new SpinnerDateModel(new Date(), null, null, Calendar.HOUR_OF_DAY));
		JSpinner.DateEditor spinnerDE2 = new JSpinner.DateEditor(spinnerHourDayOut, "HH:mm");
		spinnerHourDayOut.setEditor(spinnerDE2);
		spinnerHourDayOut.setBounds(328, 259, 97, 35);
		contentPane.add(spinnerHourDayOut);
	}
	
	//This actualices the parking lots numbers if the user changes the floor.
	public void getParkingLotsNumbers() {
		
		parkingLotsNumbersComboBoxModel.removeAllElements();
		
		for(ParkingLot pl : ParkingLotControl.getParkingLotControl().getParkingLotsList()) {
			
			if(pl.getFloor().equals(comboBoxFloor.getSelectedItem())) {
				
				parkingLotsNumbersComboBoxModel.addElement(pl.getParkingNumber());
			}
		}
	}
	
	//Implemented from "GUIUploadMethod" interface
	@SuppressWarnings("deprecation")
	public void upload() throws NullPointerException, ConcurrentModificationException {
		try {
			String carLicencePlate = textFieldlLicencePlate.getText().toUpperCase();
			String carOwner = textFieldOwner.getText().toUpperCase();
			String selectedBrand = comboBoxBrands.getSelectedItem().toString();
			String selectedFloor = comboBoxFloor.getSelectedItem().toString();
			
			ParkingLot spl = null;
			/*---------------------------------------------TIME------------------------------------------------------*/
			String dayIn = textPaneIn.getText();
			String dayOut = textPaneOut.getText();
			Date hourInDate = (Date)spinnerHourDayIn.getValue();
			Date hourOutDate = (Date)spinnerHourDayOut.getValue();
			Integer hourIn = hourInDate.getHours();
			Integer minuteIn = hourInDate.getMinutes();
			Integer hourOut = hourOutDate.getHours();
			Integer minuteOut = hourOutDate.getMinutes();
			String timeReservationIn = String.format("%02d",hourIn) + ":" + String.format("%02d", minuteIn);
			String timeReservationOut = String.format("%02d",hourOut) + ":" + String.format("%02d", minuteOut);
			int selectedParkingNumber = Integer.parseInt(comboBoxParkingLotsNumbers.getSelectedItem().toString());
			/*----------------------------------------------------------------------------------------------------*/
			//DO NOT TOUCH.
			boolean success = false;
			if(carLicencePlate.equals("")) {
				JOptionPane.showMessageDialog(null, "Ingrese los datos de la patente del vehículo!", "!", JOptionPane.WARNING_MESSAGE);
				
				success = false;
			}
			else {
				if(carOwner.equals("")) {
					JOptionPane.showMessageDialog(null, "Ingrese el nombre, apellido o DNI del dueño del vehículo!", "!", JOptionPane.WARNING_MESSAGE);
					
					success = false;
				}
				else {
					//This changes the "Occupancy" of the ParkingLot in "parkingLotsList" ArrayList on ParkingLotControl class
					for(ParkingLot pl : ParkingLotControl.getParkingLotControl().getParkingLotsList()) {
						//This gets the ParkingLot
						if(pl.getFloor().equals(selectedFloor) && pl.getParkingNumber() == selectedParkingNumber) {
							//This check if the ParkingLot is free or not.
							if(pl.isOccupancy() == false) {
					
								pl.changeOccupancy(true);
						
								pl.setReservation(dayIn, dayOut, timeReservationIn, timeReservationOut);
						
								spl = pl;
						
								success = true;
						
							}
							else {
								JOptionPane.showMessageDialog(null, "El estacionamiento está ocupado!", "!", JOptionPane.WARNING_MESSAGE);
								success = false;
							}
						}
					}
				}
			}
			//To avoid ConcurrentModificationException.
			if(success == true) {
				//Puts a new "Car" object in "CarsList" ArrayList on "ParkingLotControl" class. 
				ParkingLotControl.getParkingLotControl().addCar(new Car(carLicencePlate, carOwner, selectedBrand, spl));
				
				//Actualices the MainFrame.
				MainFrame.getMainFrame().getContentPane().removeAll();
				MainFrame.getMainFrame().initWestPanel();
				MainFrame.getMainFrame().initNorthPanel();
				MainFrame.getMainFrame().initCenterPanel(MainFrame.getMainFrame().getShowTable());
				MainFrame.getMainFrame().getContentPane().revalidate();
				MainFrame.getMainFrame().getContentPane().repaint();
				
		    	this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "No se ha podido registrar el vehiculo", "Error!", JOptionPane.ERROR_MESSAGE);
			}
	    //In case there's any ParkingLot class yet.
		}catch(NullPointerException e) {
			
			JOptionPane.showMessageDialog(null,"No se han encontrado estacionamientos en el piso seleccionado", "Error!", JOptionPane.ERROR_MESSAGE);
			
		}catch(ConcurrentModificationException e) {
			
			JOptionPane.showMessageDialog(null, "Se intentó actuar sobre 2 listas a la vez.", "Error!", JOptionPane.ERROR_MESSAGE);
		}
	}
}
