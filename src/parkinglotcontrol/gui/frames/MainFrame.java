package parkinglotcontrol.gui.frames;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.gui.panels.mainframe.DashboardButtonsPanel;
import parkinglotcontrol.gui.panels.mainframe.TablesPanel;

public class MainFrame extends JFrame {

	private static final long serialVersionUID = 8204896037972083801L;
	private JPanel contentPane;
	private static MainFrame mainFrame;
	private Dimension dimensionPantalla;
	TablesPanel tablesPanel;
	private int showTable;
	
	
	//Don´t use this builder, use ".getMainFrame()"
	private MainFrame(){	
        
		ParkingLotControl.getParkingLotControl().getMainDirectory().readFiles();	//Lee los archivos(si existen), sino crea una
																					//los archivos y/o el directorio.
		setTitle("Parking Lot Control");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		setIconImage(new ImageIcon(getClass().getResource("/parkinglotcontrol/images/logo/program_icon.png")).getImage());
		
		dimensionPantalla = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimensionPantalla.width, dimensionPantalla.height);
		setResizable(true);
		setLocationRelativeTo(null);
		setVisible(true);
		
		initMenuBar();
		
		this.addWindowListener(new WindowAdapter() {
		    public void windowClosing(WindowEvent e) {
		        handleClosing();
		    }
		});
		
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
		try {
			tablesPanel = new TablesPanel(selectedTable);
			contentPane.add(tablesPanel, BorderLayout.CENTER);
		}catch(NullPointerException e) {
			JOptionPane.showMessageDialog(null, e, "Error!", JOptionPane.ERROR_MESSAGE);
		}catch(Exception e) {
			JOptionPane.showMessageDialog(null, "No se ha podido inicializar la tabla", "Error!", JOptionPane.ERROR_MESSAGE);
		}

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
		
		/*----------------------------fileMenu Items---------------------------------*/
			JMenuItem fileAddCarItem0 = new JMenuItem("Añadir Vehiculo");
			JMenuItem fileAddParkingItem1 = new JMenuItem("Añadir Estacionamiento");
				
			fileMenu.add(fileAddCarItem0);
			fileMenu.add(fileAddParkingItem1);
		/*--------------------------------------------------------------------------*/
		
		/*----------------------------helpMenu Items---------------------------------*/
			JMenuItem menuHelpItem = new JMenuItem("Acerca de");
			
			helpMenu.add(menuHelpItem);
		/*--------------------------------------------------------------------------*/
			
		fileAddCarItem0.addActionListener((ActionEvent e) -> {
			new CarUploadFrame();
		});
		
		fileAddParkingItem1.addActionListener((ActionEvent e) -> {
			new ParkingLotUploadFrame();
		});
		
		menuHelpItem.addActionListener((ActionEvent e) -> {
			
		});
		
		/*-----------------------------------------Accelerators-----------------------------------------------*/
		fileAddCarItem0.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK));
		fileAddParkingItem1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_DOWN_MASK));
		menuHelpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK));
		/*---------------------------------------------------------------------------------------------------*/
	}
	
	
	//Use this to manage program shut down.
	public void handleClosing() {
		int n = JOptionPane.showConfirmDialog(null,"¿Desea salir?" ,"!", JOptionPane.OK_CANCEL_OPTION, JOptionPane.WARNING_MESSAGE);
	    if(n == JOptionPane.OK_OPTION) {

	    	ParkingLotControl.getParkingLotControl().getMainDirectory().writeFiles();
	    	
		    System.exit(getDefaultCloseOperation());
		}       
	}     
}


