package parkinglotcontrol.gui.frames;

import java.awt.BorderLayout;
import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import parkinglotcontrol.enums.CarBrands;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.ListSelectionModel;

public class CarUploadFrame extends JFrame {

	private static final long serialVersionUID = -294565520020423779L;
	private JPanel contentPane;
	private DefaultComboBoxModel<String> carBrandsComboBoxModel;
	private JComboBox<String> comboBoxBrands;
	private JTextField textField;
	private JTextField textField_1;

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
		
		JLabel lblTitle = new JLabel("Registro de auto");
		lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblTitle.setBounds(146, 10, 198, 46);
		contentPane.add(lblTitle);
		
		initJComboBoxes();
		
		initLabels();
	}
	
	public void initLabels() {
		
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
		
		textField = new JTextField();
		textField.setBounds(87, 105, 153, 36);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(87, 146, 153, 36);
		contentPane.add(textField_1);
		
			
	}
	
	public void initTextAreas() {
		
	}
	
	public void initJComboBoxes() {
		carBrandsComboBoxModel = new DefaultComboBoxModel<String>();
		
		for(CarBrands cb : CarBrands.values()) {
			carBrandsComboBoxModel.addElement(cb.brandTitle);
		}
		
		comboBoxBrands = new JComboBox<String>(carBrandsComboBoxModel);
		comboBoxBrands.setBounds(87, 195, 153, 35);
		
		contentPane.add(comboBoxBrands);
		
	}
}
