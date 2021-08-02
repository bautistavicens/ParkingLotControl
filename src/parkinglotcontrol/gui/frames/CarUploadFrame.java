package parkinglotcontrol.gui.frames;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
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
		initTextFiles();
		initButtons();
		getParkingLotsNumbers();
		
		
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
	
	public void initTextFiles() {

		textFieldlLicencePlate = new JTextField();
		textFieldlLicencePlate.setBounds(87, 105, 153, 36);
		contentPane.add(textFieldlLicencePlate);
		textFieldlLicencePlate.setColumns(10);
		
		textFieldOwner = new JTextField();
		textFieldOwner.setColumns(10);
		textFieldOwner.setBounds(87, 146, 153, 36);
		contentPane.add(textFieldOwner);
		
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
	
	public void getParkingLotsNumbers() {
		
		parkingLotsNumbersComboBoxModel.removeAllElements();
		
		for(ParkingLot pl : ParkingLotControl.getParkingLotControl().getParkingLotsList()) {
			
			if(pl.getFloor().equals(comboBoxFloor.getSelectedItem())) {
				
				parkingLotsNumbersComboBoxModel.addElement(pl.getParkingNumber());
			}
		}
	}
	
	public void initButtons() {
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/green_check_icon.png")));
		btnAceptar.setBounds(146, 374, 85, 57);
		btnAceptar.setContentAreaFilled(false);
		btnAceptar.setBorderPainted(false);
		btnAceptar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAceptar.setHorizontalTextPosition(SwingConstants.CENTER);
		btnAceptar.addActionListener((ActionEvent e)-> {
		     int n = JOptionPane.showConfirmDialog(null,"¿Cargar datos?" ,"!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		     if(n == JOptionPane.OK_OPTION) {
		    	
		    	this.upload();
		    	 
		    	this.dispose();
		     }
		});
		contentPane.add(btnAceptar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/red_cancel_icon.png")));
		btnCancelar.setBounds(259, 374, 85, 57);
		btnCancelar.setContentAreaFilled(false);
		btnCancelar.setBorderPainted(false);
		btnCancelar.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancelar.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnCancelar.addActionListener((ActionEvent e)-> {
		     int n = JOptionPane.showConfirmDialog(null,"¿Cancelar operación?" ,"!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		     if(n == JOptionPane.OK_OPTION) {
		    	this.dispose();
		     }
		});
		
		contentPane.add(btnCancelar);
	}

	public void upload() {
		
		String carLicencePlate = textFieldlLicencePlate.getText();
		String carOwner = textFieldOwner.getText();
		String selectedBrand = comboBoxBrands.getSelectedItem().toString();
		String selectedFloor = comboBoxFloor.getSelectedItem().toString();
		int selectedParkingNumber = Integer.parseInt(comboBoxParkingLotsNumbers.getSelectedItem().toString());
		int plIndex = -1;
		
		//This changes the state of the ParkingLot in "parkingLotsList" ArrayList on ParkingLotControl class
		for(ParkingLot pl : ParkingLotControl.getParkingLotControl().getParkingLotsList()) {
			if(pl.getFloor().equals(selectedFloor) && pl.getParkingNumber() == selectedParkingNumber) {
				pl.changeOccupancy(false);
				plIndex = plIndex + 1;
			}
		}
		
		ParkingLotControl.getParkingLotControl().addCar(new Car(carLicencePlate, carOwner, selectedBrand, ParkingLotControl.getParkingLotControl().getParkingLotsList().get(plIndex)));
		
	}
}
