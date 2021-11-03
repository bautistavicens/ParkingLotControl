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
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.interfaces.GuiUploadMethod;
import parkinglotcontrol.models.ParkingLot;

public class ParkingLotUploadFrame extends JFrame implements GuiUploadMethod {

	private static final long serialVersionUID = -4928689092388813531L;
	private JPanel contentPane;
	private String[] floors = {"PB", "1", "2", "3", "4", "5", "6", "7", "8"};
	private JComboBox<String> comboBoxFloor;
	JSpinner spinner;
	public ParkingLotUploadFrame() {
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(false);
		this.setSize(400, 400);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		this.setIconImage(new ImageIcon(getClass().getResource("/parkinglotcontrol/images/logo/program_icon.png")).getImage());
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cantidad");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(28, 119, 104, 26);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Registro de estacionamiento");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel_1.setBounds(28, 10, 345, 66);
		contentPane.add(lblNewLabel_1);
		
		spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(1, 1, 50, 1));
		spinner.setBounds(111, 118, 53, 38);
		contentPane.add(spinner);
		
		JLabel lblNewLabel_2 = new JLabel("Piso");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_2.setBounds(28, 198, 45, 19);
		contentPane.add(lblNewLabel_2);
		
		comboBoxFloor = new JComboBox<String>();
		comboBoxFloor.setModel(new DefaultComboBoxModel<String>(floors));
		comboBoxFloor.setBounds(111, 192, 53, 38);
		contentPane.add(comboBoxFloor);
		
		this.initButtons();
		
	}

	public void initButtons() {
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/green_check_icon.png")));
		btnAceptar.setBounds(94, 287, 85, 57);
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
		btnCancelar.setBounds(196, 287, 85, 57);
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
    	
		String selectedFloor = comboBoxFloor.getSelectedItem().toString();
    	int selectedParkingLots  = Integer.parseInt(spinner.getValue().toString());
    	int lastOnList = ParkingLotControl.getParkingLotControl().getParkingLotsList().size() + 1;
    	
    	for(int i=0; i < selectedParkingLots; i++) {
    		ParkingLotControl.getParkingLotControl().addParkingLot(new ParkingLot(selectedFloor, (i+lastOnList)));
    	}
		
    	MainFrame.getMainFrame().actualiceMainFrame();
		
	}
	
	
}
