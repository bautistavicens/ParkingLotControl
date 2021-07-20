package parkinglotcontrol.gui.panels.mainframe;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.gui.frames.MainFrame;
import parkinglotcontrol.tables.TableCar;
import parkinglotcontrol.tables.TableParking;

public class TablesPanel extends JPanel {

	private static final long serialVersionUID = 2796366492893053210L;
	private JTable tableParking;
	private JTable tableCar;
	private JScrollPane scrollPaneTableParking;
	private int selectedRow = -1;
	
 	public TablesPanel(int selectedTable) {
		
		this.setLayout(new BorderLayout());
		
		this.initPanel(selectedTable);
	}


 	public void initPanel(int table) {
 		//Tables:
 			//Parking lots 0
 			//Cars 1
 		if (table == 0) {
 			tableParking = new JTable(new TableParking(ParkingLotControl.getParkingLotControl().getParkingLotsList()));
		
 			this.add(tableParking, BorderLayout.CENTER);
		
 			tableParking.addMouseListener(new MouseAdapter() {
			
 				//Mouse listens to user clicks on rows.
 				public void mouseClicked(java.awt.event.MouseEvent evt) {
				
				selectedRow = tableParking.rowAtPoint(evt.getPoint());
 				}
 			});
	
 			scrollPaneTableParking = new JScrollPane(tableParking);
 			this.add(scrollPaneTableParking);
	
 			JButton btnNewButton = new JButton("Eliminar");
 			btnNewButton.addActionListener((ActionEvent e) ->{
		
 				if(selectedRow >= 0) {
 					//The program gets the row that user selected and deletes it from the main list.
 					ParkingLotControl.getParkingLotControl().getParkingLotsList().remove(selectedRow);
 		    		
 					MainFrame.getMainFrame().getContentPane().removeAll();
 		        	MainFrame.getMainFrame().initWestPanel();
 		        	MainFrame.getMainFrame().initNorthPanel();
 		        	MainFrame.getMainFrame().initCenterPanel(MainFrame.getMainFrame().getShowTable());
 		    		MainFrame.getMainFrame().getContentPane().revalidate();
 		    		MainFrame.getMainFrame().getContentPane().repaint();
 				}
 				else {
 					JOptionPane.showMessageDialog(null, "Seleccione una fila!", "Parking Lot Control", JOptionPane.INFORMATION_MESSAGE);
 				}
 			});
 			this.add(btnNewButton, BorderLayout.NORTH);
 		}
	
 		else {
		
 			tableCar = new JTable(new TableCar(ParkingLotControl.getParkingLotControl().getCarsList()));

 			this.add(tableCar, BorderLayout.CENTER);
		
 			tableCar.addMouseListener(new MouseAdapter() {
		
 				//Mouse listens to user clicks on rows.
 				public void mouseClicked(java.awt.event.MouseEvent evt) {
				
 					selectedRow = tableCar.rowAtPoint(evt.getPoint());
 				}
 			});
		
 			scrollPaneTableParking = new JScrollPane(tableCar);
		
 			this.add(scrollPaneTableParking);
		
 		}
 	}
}