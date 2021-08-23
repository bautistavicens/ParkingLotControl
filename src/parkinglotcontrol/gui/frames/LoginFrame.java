package parkinglotcontrol.gui.frames;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class LoginFrame extends JFrame {

	/**
	 * 
	 */
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
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
	public void initLabels() {
		
		JLabel lblUser = new JLabel("Usuario");
		lblUser.setForeground(Color.WHITE);
		lblUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblUser.setBounds(10, 89, 60, 28);
		contentPane.add(lblUser);
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
}
