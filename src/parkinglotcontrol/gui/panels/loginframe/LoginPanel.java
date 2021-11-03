package parkinglotcontrol.gui.panels.loginframe;

import java.awt.Color;
import java.awt.Font;
import java.util.Arrays;

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
import parkinglotcontrol.gui.frames.MainFrame;
import parkinglotcontrol.models.User;
import textprompt.TextPrompt;

public class LoginPanel extends JPanel {

	private static final long serialVersionUID = 1096861959201942703L;
	private LoginFrame lFrame;
	private JTextField UserEmailTextField;
	private JPasswordField passwordField;

	public LoginPanel(LoginFrame lFrame) {
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
	
		JLabel lblWelcome = new JLabel("Bienvenido");
		lblWelcome.setFont(new Font("Tahoma", Font.PLAIN, 24));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(94, 27, 173, 32);
		add(lblWelcome);
		
		JLabel lblUserIcon = new JLabel();
		lblUserIcon.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/labels/user_icon_log.png")));
		lblUserIcon.setBounds(33, 87, 31, 32);
		this.add(lblUserIcon);
		
		JLabel lblPasswordIcon = new JLabel();
		lblPasswordIcon.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/labels/password_icon_log.png")));
		lblPasswordIcon.setBounds(33, 145, 31, 32);
		this.add(lblPasswordIcon);
		
	}
	
	public void initTextFields() {
		
		UserEmailTextField = new JTextField();
		UserEmailTextField.setBounds(74, 91, 217, 26);
		UserEmailTextField.setColumns(10);
		this.add(UserEmailTextField);
		
		addPlaceholders(UserEmailTextField, "Email/Usuario");
		
		passwordField = new JPasswordField();
		passwordField.setBounds(74, 149, 217, 28);
		this.add(passwordField);
		
		addPlaceholders(passwordField, "Contraseña");
	}
	
	public void initCheckBoxes() {
		JCheckBox chckbxShow = new JCheckBox("Mostrar");
		chckbxShow.setFont(new Font("Tahoma", Font.PLAIN, 12));
		chckbxShow.setBounds(228, 183, 69, 26);
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
		
		JButton btnLogIn = new JButton();
		btnLogIn.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogIn.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/buttons/login_icon.png")));
		btnLogIn.setBounds(87, 231, 52, 41);
		btnLogIn.setContentAreaFilled(false);
		btnLogIn.setBorderPainted(false);
		btnLogIn.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnLogIn.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnLogIn.addActionListener(ActionEvent -> {
			this.userAuthentication();
		});	
		
		this.add(btnLogIn);
		
		JButton btnAddUser = new JButton();
		btnAddUser.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnAddUser.setIcon(new ImageIcon(LoginFrame.class.getResource("/parkinglotcontrol/images/buttons/add_user_icon.png")));
		btnAddUser.setBounds(208, 233, 52, 32);
		btnAddUser.setContentAreaFilled(false);
		btnAddUser.setBorderPainted(false);
		btnAddUser.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnAddUser.setHorizontalTextPosition(SwingConstants.CENTER);
		
		btnAddUser.addActionListener(ActionEvent -> {
			lFrame.initRegisterPanel();
		});	
		
		this.add(btnAddUser);
		
		JButton btnForgotPassword = new JButton("\u00BFHas olvidado tu contrase\u00F1a?");
		btnForgotPassword.setFont(new Font("Tahoma", Font.PLAIN, 10));
		btnForgotPassword.setBounds(43, 186, 179, 22);
		btnForgotPassword.setContentAreaFilled(false);
		btnForgotPassword.setBorderPainted(false);
		this.add(btnForgotPassword);

		btnForgotPassword.addActionListener(ActionEvent -> {
			JOptionPane.showMessageDialog(null, "Contacte al proveedor del software para restablecer la contraseña");
		});	
	}
	
	public void addPlaceholders(JTextField textField, String message) {
		TextPrompt placeholder = new TextPrompt(message, textField);
		placeholder.changeAlpha(0.75f);
		placeholder.changeStyle(Font.ITALIC);
	}
	
	private char[] passwordDecrypt(byte[] password, SecretKey key) {
		try {
		
			Cipher cipher = Encryptor.cipherGen();
		
			char[] passwordDecrypted = Encryptor.decryptData(password, key, cipher);
			
			return passwordDecrypted;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public void userAuthentication() {
		
		String emailUsername = UserEmailTextField.getText().trim();
		
		for(User user : ParkingLotControl.getParkingLotControl().getUsersList()) {
			
			if(user.getEmail().equals(emailUsername) || user.getUsername().equals(emailUsername)) {
				
				if(Arrays.equals(passwordField.getPassword(), passwordDecrypt(user.getPassword(), user.getKey())) == true){

					ParkingLotControl.getParkingLotControl().setLogedUser(user);
					
					MainFrame.getMainFrame();
					
					lFrame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error!", JOptionPane.ERROR_MESSAGE);
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "El usuario no está registrado", "!", JOptionPane.WARNING_MESSAGE);
			}
		}
		
	}
}
