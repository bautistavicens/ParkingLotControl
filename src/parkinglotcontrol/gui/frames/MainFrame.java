package parkinglotcontrol.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import parkinglotcontrol.gui.panels.mainframe.DashboardButtonsPanel;
import parkinglotcontrol.gui.panels.mainframe.TablesPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8204896037972083801L;
	private JPanel contentPane;
	private static MainFrame mainFrame;
	private Dimension dimensionPantalla;
	private int showTable;
	
	
	//Don�t use this builder, use ".getMainFrame()"
	private MainFrame(){	
		setTitle("Parking Lot Control");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setIconImage(new ImageIcon(getClass().getResource("/parkinglotcontrol/images/logo/program_icon.png")).getImage());
		
		dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimensionPantalla.width, dimensionPantalla.height);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
		initMenuBar();
		
		contentPane = new JPanel();
		contentPane.setForeground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		//First table to show
		this.showTable = 0;
		
		setContentPane(contentPane);
		getContentPane().removeAll();
		initWestPanel();
		initNorthPanel();
		initCenterPanel(showTable);
		getContentPane().revalidate();
		getContentPane().repaint();
		
	}
	
	//use this method to build the class
	public static MainFrame getMainFrame() {	 
		if (mainFrame==null) {
			try {	 
				mainFrame=new MainFrame();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
			 
		return mainFrame;
	}

	
	public int getShowTable() {
		return showTable;
	}

	public void setShowTable(int showTable) {
		this.showTable = showTable;
	}

	
	public void initNorthPanel() {
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 128, 128));
		contentPane.add(panel, BorderLayout.NORTH);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("Parking Lot Control");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("Bautista Vicens \u00A9");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		panel.add(lblNewLabel_1, BorderLayout.SOUTH);
		
	}
	
	public void initCenterPanel(int selectedTable) {
		TablesPanel tablesPanel = new TablesPanel(selectedTable);
		contentPane.add(tablesPanel, BorderLayout.CENTER);

	}
	
	public void initWestPanel() {
		DashboardButtonsPanel dashboardButtonsPanel = new DashboardButtonsPanel();
		
		contentPane.add(dashboardButtonsPanel, BorderLayout.WEST);

	}
	
	public void initMenuBar(){
		JMenuBar menuBar = new JMenuBar();
		
		setJMenuBar(menuBar);
		
		JMenu fileMenu = new JMenu("Archivo");
		JMenu helpMenu = new JMenu("Ayuda");
		
		menuBar.add(fileMenu);
		menuBar.add(helpMenu);
		
		JMenuItem menuAddParkingItem1 = new JMenuItem("A�adir Estacionamiento");
		JMenuItem menuSaveItem2 = new JMenuItem("Guardar");
		JMenuItem menuHelpItem3 = new JMenuItem("?");

		menuAddParkingItem1.addActionListener((ActionEvent e) -> {
			new ParkingLotUploadFrame();
		});
		menuSaveItem2.addActionListener((ActionEvent e) -> {
		});
		menuHelpItem3.addActionListener((ActionEvent e) -> {
			
		});
		
		menuAddParkingItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		menuSaveItem2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK));
		menuHelpItem3.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		
		fileMenu.add(menuAddParkingItem1);
		fileMenu.add(menuSaveItem2);
		helpMenu.add(menuHelpItem3);
		
	}
}


