package parkinglotcontrol.gui.panels.loginframe;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import encryptor.crypto.Encryptor;
import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.gui.frames.LoginFrame;
import parkinglotcontrol.gui.frames.ParkingLotUploadFrame;
import parkinglotcontrol.interfaces.GuiUploadMethod;
import parkinglotcontrol.models.User;

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
		
		JLabel lblRegistry = new JLabel("Registro");
		lblRegistry.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistry.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblRegistry.setBounds(96, 10, 173, 32);
		add(lblRegistry);
		
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
		usernameTextField.setBounds(127, 77, 201, 23);
		add(usernameTextField);
		usernameTextField.setColumns(10);
		
		emailTextField = new JTextField();
		emailTextField.setBounds(127, 135, 201, 23);
		add(emailTextField);
		emailTextField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(127, 187, 201, 23);
		add(passwordField);
	}
	
	public void initCheckBoxes() {
		JCheckBox chckbxShow = new JCheckBox("Mostrar");
		chckbxShow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxShow.setBounds(259, 212, 69, 26);
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
				
		    	//Change this in the future.
		    	if(ParkingLotControl.getParkingLotControl().getUsersList().size() > 0) {
		    		JOptionPane.showMessageDialog(null, "Ya existe un usuario en el sistema.", "!", JOptionPane.WARNING_MESSAGE);
		    	}
		    	else {
		    		this.upload(); 
		    	}
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
		
	}
	
	
	private boolean emailChecker(String email) {
		if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(null, "E-mail invalido!", "!", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		return true;
	}
	
	
	private boolean usernameChecker(String username) {
		
		if(username.length() < MIN_USER_PASSWORD_LENGTH || username.length() >= MAX_USER_PASSWORD_LENGTH) {
			JOptionPane.showMessageDialog(null, "El nombre de usuario debe tener 6-32 caracteres de largo!", "!", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		for(String PC : PROHIBITED_CHARACTERS_USERNAME) {
			if(username.contains(PC)) {
				JOptionPane.showMessageDialog(null, "El nombre de usuario tiene caracteres no validos!", "!", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		return true;
	}
	
	
	public boolean checkUserExistance(String email, String username) {
		for(User user : ParkingLotControl.getParkingLotControl().getUsersList()) {
			if(user.getUsername().equals(username)) {
				JOptionPane.showMessageDialog(null, "El nombre de usuario ya se encuentra registrado.", "!", JOptionPane.WARNING_MESSAGE);
				return true;
			}
			if(user.getEmail().equals(email)) {
				JOptionPane.showMessageDialog(null, "El email ya se encuentra registrado.", "!", JOptionPane.WARNING_MESSAGE);
				return true;
			}
		}
		
		return false;
		}
	
	
	private boolean passwordChecker(char [] password) {	
		
		if(password.length < MIN_USER_PASSWORD_LENGTH || password.length >= MAX_USER_PASSWORD_LENGTH) {
			JOptionPane.showMessageDialog(null, "La contraseña debe tener 6-32 caracteres de largo!", "!", JOptionPane.WARNING_MESSAGE);
			return false;
		}
		
		for(int i=0; i < password.length; i++) {
			if(password[i] == ' ') {
				JOptionPane.showMessageDialog(null, "La contraseña no debe contener espacios!", "!", JOptionPane.WARNING_MESSAGE);
				return false;
			}
		}
		 
		return true;
	}
	
	private byte[] passwordEncrypt(char[] password, SecretKey key) {
		
		try {
		
			Cipher cipher = Encryptor.cipherGen();
		
			byte[] passwordEncrypted = Encryptor.encryptData(password, key, cipher);
			
			return passwordEncrypted;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	
	public void upload() {
		try {
			String username = usernameTextField.getText().trim();
			String email = emailTextField.getText().trim();
			char[] password = passwordField.getPassword();
			SecretKey key = Encryptor.keyGen();
		
			if (emailChecker(email) == true) {
			
				if(passwordChecker(password) == true) {
				
					if(usernameChecker(username) == true) {
						
						if(checkUserExistance(email, username) == false) {
					
							byte[] passwordEncrypted = passwordEncrypt(password, key);
					
							ParkingLotControl.getParkingLotControl().registerUser(username, email, passwordEncrypted, key);
					
							JOptionPane.showMessageDialog(null, "Usuario creado!", "!", JOptionPane.INFORMATION_MESSAGE);
					
							lFrame.initLoginPanel();
					
						}
					}
				}
			}
		} catch (Exception e) {
			
			JOptionPane.showMessageDialog(null, "No se ha podido registrar al usuario!", "Fatal Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
