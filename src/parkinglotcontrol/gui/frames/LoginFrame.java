package parkinglotcontrol.gui.frames;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.gui.panels.loginframe.LoginPanel;
import parkinglotcontrol.gui.panels.loginframe.RegisterPanel;

public class LoginFrame extends JFrame {

	private static final long serialVersionUID = 9050029824392548218L;
	private JPanel contentPane;

	public LoginFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(368, 362);
		setTitle("Parking Lot Control");
		setIconImage(new ImageIcon(getClass().getResource("/parkinglotcontrol/images/logo/program_icon.png")).getImage());
		setLocationRelativeTo(null);
		setResizable(false);
		setVisible(true);
		
		ParkingLotControl.getParkingLotControl().getMainDirectory().readFiles();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(new Color(0, 128, 128));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		initLoginPanel();
		
	}
	
	public void initLoginPanel() {
		LoginPanel lPanel = new LoginPanel(this);
		getContentPane().removeAll();
		getContentPane().add(lPanel);
		getContentPane().revalidate();
		getContentPane().repaint();
		
	}
	
	public void initRegisterPanel() {
		RegisterPanel rPanel = new RegisterPanel(this);
		getContentPane().removeAll();
		getContentPane().add(rPanel);
		getContentPane().revalidate();
		getContentPane().repaint();
	}
	
}
	
