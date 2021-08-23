package parkinglotcontrol.gui.frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 9050029824392548218L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(368, 362);
		setTitle("Parking Lot Control");
		setLocationRelativeTo(null);
		setVisible(true);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 128, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		getContentPane().removeAll();
		initLabels();
		initTextFields();
		initCheckBoxes();
		initButtons();
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	public void initLabels() {
		
		JLabel lblUserIcon = new JLabel();
		lblUserIcon.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/labels/user_icon_log.png")));
		lblUserIcon.setBounds(33, 87, 31, 32);
		contentPane.add(lblUserIcon);
		
		JLabel lblPasswordIcon = new JLabel();
		lblPasswordIcon.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/labels/password_icon_log.png")));
		lblPasswordIcon.setBounds(33, 145, 31, 32);
		contentPane.add(lblPasswordIcon);
		
	}
	
	public void initTextFields() {
		
		textField = new JTextField();
		textField.setBounds(74, 91, 217, 26);
		textField.setColumns(10);
		contentPane.add(textField);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(74, 149, 217, 28);
		contentPane.add(passwordField);
		
	}
	
	public void initCheckBoxes() {
		JCheckBox chckbxShow = new JCheckBox("Mostrar");
		chckbxShow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxShow.setBounds(222, 183, 69, 26);
		chckbxShow.setBackground(new Color(0, 128, 128));
		contentPane.add(chckbxShow);
	
		chckbxShow.addActionListener(ActionEvent -> {
			if(chckbxShow.isSelected()) {
				passwordField.setEchoChar((char)0);
			}
			else {
				passwordField.setEchoChar('*');
			}
		});
	}
	
	public void initButtons() {
		JButton btnLogIn = new JButton();
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogIn.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/buttons/login_icon.png")));
		btnLogIn.setBounds(87, 231, 52, 41);
		btnLogIn.setContentAreaFilled(false);
		btnLogIn.setBorderPainted(false);
		btnLogIn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLogIn.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnLogIn.addActionListener(ActionEvent ->{
			MainFrame.getMainFrame();
			this.dispose();
		});	
		
		contentPane.add(btnLogIn);
		
		JButton btnAddUser = new JButton();
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddUser.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/buttons/add_user_icon.png")));
		btnAddUser.setBounds(208, 233, 52, 32);
		btnAddUser.setContentAreaFilled(false);
		btnAddUser.setBorderPainted(false);
		btnAddUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAddUser.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnAddUser.addActionListener(ActionEvent ->{
			
		});	
		
		contentPane.add(btnAddUser);
	}
}
