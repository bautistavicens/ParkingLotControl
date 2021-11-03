package parkinglotcontrol.gui.panels.mainframe;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import parkinglotcontrol.gui.frames.MainFrame;

public class DashboardButtonsPanel extends JPanel {

	private static final long serialVersionUID = -3898629215296154594L;

	public DashboardButtonsPanel() {
		
		this.setBackground(new Color(0, 128, 128));
		
		GridBagLayout gbl_panel1 = new GridBagLayout();
		gbl_panel1.columnWidths = new int[]{129, 0};
		gbl_panel1.rowHeights = new int[]{35, 50, 35, 0};
		gbl_panel1.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel1.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		this.setLayout(gbl_panel1);
		
		
		JButton btnParking = new JButton();
		
		//Parking button properties.
		btnParking.setText("Estacionamiento");
		btnParking.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnParking.setHorizontalTextPosition(SwingConstants.CENTER);
		btnParking.setBorderPainted(false);
		btnParking.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/parking_icon_small.png")));
		btnParking.setContentAreaFilled(false);
		
		//Parking button Listener.
		btnParking.addMouseListener(new MouseListener() {
				
			public void mouseExited(MouseEvent e) {
				btnParking.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/parking_icon_small.png")));
			}
				
			public void mouseEntered(MouseEvent e) {
				btnParking.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/parking_icon_big.png")));		
			}
			
			public void mouseClicked(MouseEvent e) {
				MainFrame.getMainFrame().setShowTable(0);
				MainFrame.getMainFrame().actualiceMainFrame();
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
				
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
					
			}
		});
		
		//Parking button ubication.
		GridBagConstraints gbc_btnParking = new GridBagConstraints();
		gbc_btnParking.fill = GridBagConstraints.BOTH;
		gbc_btnParking.insets = new Insets(0, 0, 5, 0);
		gbc_btnParking.gridx = 0;
		gbc_btnParking.gridy = 0;
		this.add(btnParking, gbc_btnParking);
		
		JButton btnCarParking = new JButton("Autos");
		
		//Car parking button properties.
		btnCarParking.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnCarParking.setHorizontalTextPosition(SwingConstants.CENTER);
		btnCarParking.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/car_icon_small.png")));
		btnCarParking.setContentAreaFilled(false);
		btnCarParking.setBorderPainted(false);
		
		//Car parking ubication.
		GridBagConstraints gbc_btnCarParking = new GridBagConstraints();
		gbc_btnCarParking.insets = new Insets(0, 0, 5, 0);
		gbc_btnCarParking.gridx = 0;
		gbc_btnCarParking.gridy = 1;
		this.add(btnCarParking, gbc_btnCarParking);
		
		
		
		JButton btnExit = new JButton("Salir");
		
		//Exit button properites.
		btnExit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnExit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnExit.setBorderPainted(false);
		btnExit.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/exit_icon_small.png")));
		btnExit.setContentAreaFilled(false);
		
		//Car parking button Listener.
		btnCarParking.addMouseListener(new MouseListener() {
			
			public void mouseExited(MouseEvent e) {
				btnCarParking.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/car_icon_small.png")));
			}
			
			public void mouseEntered(MouseEvent e) {
				btnCarParking.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/car_icon_big.png")));
				
			}

			public void mouseClicked(MouseEvent e) {

				MainFrame.getMainFrame().setShowTable(1);
				MainFrame.getMainFrame().actualiceMainFrame();
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		//Exit button ubiction.
		GridBagConstraints gbc_btnExit = new GridBagConstraints();
		gbc_btnExit.fill = GridBagConstraints.BOTH;
		gbc_btnExit.insets = new Insets(0, 0, 5, 0);
		gbc_btnExit.gridx = 0;
		gbc_btnExit.gridy = 2;
		this.add(btnExit, gbc_btnExit);
		
		//Exit Button Listener.
		btnExit.addMouseListener(new MouseListener() {
			
			public void mouseExited(MouseEvent e) {
				btnExit.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/exit_icon_small.png")));
			}
			
			public void mouseEntered(MouseEvent e) {
				btnExit.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/exit_icon_big.png")));
				
			}

			public void mouseClicked(MouseEvent e) {
				MainFrame.getMainFrame().handleClosing();
				
			}

			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}

			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
}
