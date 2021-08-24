package parkinglotcontrol.gui.panels.loginframe;

import java.awt.Color;

import javax.swing.JPanel;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.gui.frames.LoginFrame;
import parkinglotcontrol.gui.frames.ParkingLotUploadFrame;
import parkinglotcontrol.interfaces.GuiUploadMethod;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;

public class RegisterPanel extends JPanel implements GuiUploadMethod {

	private static final long serialVersionUID = -5130176954516822385L;
	private LoginFrame lFrame;
	private JTextField usernameTextField;
	private JTextField emailTextField;
	private JPasswordField passwordField;
	private final int MIN_USER_PASSWORD_LENGTH = 6;
	private final int MAX_USER_PASSWORD_LENGTH = 32;
	private final String[] PROHIBITED_CHARACTERS_USERNAME = {"@", "$", "+", "-", "/", "_", "%", "?", "^"};

	public RegisterPanel(LoginFrame lFrame) {
		this.setSize(368, 362);
		this.setVisible(true);
		this.setLayout(null);
		this.setBackground(new Color(0, 128, 128));
		
		this.lFrame = lFrame;
		
		this.initLabels();
		this.initTextFields();
		this.initCheckBoxes();
		this.initButtons();
		
	}
	public void initLabels() {
		JLabel lblUsername = new JLabel("Usuario");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUsername.setBounds(26, 74, 65, 23);
		add(lblUsername);
		
		JLabel lblPassword = new JLabel("Contrase\u00F1a");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPassword.setBounds(26, 187, 91, 23);
		add(lblPassword);
		
		JLabel lblemail = new JLabel("Email");
		lblemail.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblemail.setBounds(26, 132, 45, 23);
		add(lblemail);
	}
	
	public void initTextFields() {
		
		usernameTextField = new JTextField();
		usernameTextField.setBounds(101, 74, 201, 23);
		add(usernameTextField);
		usernameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(101, 132, 201, 23);
		add(emailTextField);
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 187, 175, 23);
		add(passwordField);
	}
	
	public void initCheckBoxes() {
		JCheckBox chckbxShow = new JCheckBox("Mostrar");
		chckbxShow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxShow.setBounds(233, 210, 69, 26);
		chckbxShow.setBackground(new Color(0, 128, 128));
		this.add(chckbxShow);
	
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
		JButton btnOk = new JButton("Aceptar");
		btnOk.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/green_check_icon.png")));
		btnOk.setBounds(80, 255, 85, 57);
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
		
		this.add(btnOk);
		
		JButton btnCancel = new JButton("Cancelar");
		btnCancel.setIcon(new ImageIcon(ParkingLotUploadFrame.class.getResource("/parkinglotcontrol/images/buttons/red_cancel_icon.png")));
		btnCancel.setBounds(190, 255, 85, 57);
		btnCancel.setContentAreaFilled(false);
		btnCancel.setBorderPainted(false);
		btnCancel.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnCancel.addActionListener((ActionEvent e)-> {
			int n = JOptionPane.showConfirmDialog(null,"¿Cancelar operación?" ,"!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
		    if(n == JOptionPane.OK_OPTION) {
		    	
		    	lFrame.initLoginPanel();
		    	
		    }
		});
		
		this.add(btnCancel);
		
		JLabel lblRegistry = new JLabel("Registro");
		lblRegistry.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistry.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegistry.setBounds(96, 10, 173, 32);
		add(lblRegistry);
	}
	
	public boolean emailChecker(String email) {
		if (!email.contains("@") || !email.contains(".")) {
			return false;
		}
		return true;
	}
	public boolean usernameChecker(String username) {
		
		if(username.length() < MIN_USER_PASSWORD_LENGTH || username.length() >= MAX_USER_PASSWORD_LENGTH) {
			return false;
		}
		
		for(String PC : PROHIBITED_CHARACTERS_USERNAME) {
			if(username.contains(PC)) {
				return false;
			}
		}
		return true;
	}
	public boolean passwordChecker(char [] password) {	
		
		if(password.length < MIN_USER_PASSWORD_LENGTH || password.length >= MAX_USER_PASSWORD_LENGTH) {
			return false;
		}
		
		for(int i=0; i < password.length; i++) {
			if(password[i] == ' ') {
				return false;
			}
		}
		 
		return true;
	}
	public void upload() {
		String username = usernameTextField.getText().trim();
		String email = emailTextField.getText().trim();
		char[] password = passwordField.getPassword();
		if (emailChecker(email) == true) {
			
			if(passwordChecker(password) == true) {
				
				if(usernameChecker(username) == true) {
					
					ParkingLotControl.getParkingLotControl().registerUser(username, email, password);
					
					JOptionPane.showMessageDialog(null, "Usuario creado!", "!", JOptionPane.INFORMATION_MESSAGE);
					
					lFrame.initLoginPanel();
					
				}
				else {
					JOptionPane.showMessageDialog(null, "Nombre de usuario no valido!", "!", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Contraseña invalida!", "!", JOptionPane.WARNING_MESSAGE);
			}
		}
		else {
			JOptionPane.showMessageDialog(null, "E-mail no valido!", "!", JOptionPane.WARNING_MESSAGE);
		}
	}
}
