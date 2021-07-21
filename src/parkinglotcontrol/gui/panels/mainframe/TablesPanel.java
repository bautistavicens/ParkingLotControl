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
	private JPanel btnsPanel;
	
 	public TablesPanel(int selectedTable) {
		
		this.setLayout(new BorderLayout());
		
		this.btnsPanel = new JPanel();
		
		this.add(btnsPanel, BorderLayout.NORTH);
		
		this.initPanel(selectedTable);
		
		this.initButtons(selectedTable);
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
 	public void initButtons(int selectedTable) {
 		JButton btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener((ActionEvent e) ->{	
			if(this.selectedRow >= 0) {
				if(selectedTable == 0) {
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
					ParkingLotControl.getParkingLotControl().getCarsList().remove(selectedRow);
		    		
					MainFrame.getMainFrame().getContentPane().removeAll();
					MainFrame.getMainFrame().initWestPanel();
					MainFrame.getMainFrame().initNorthPanel();
					MainFrame.getMainFrame().initCenterPanel(MainFrame.getMainFrame().getShowTable());
					MainFrame.getMainFrame().getContentPane().revalidate();
					MainFrame.getMainFrame().getContentPane().repaint();
				}
			}
			else {
				JOptionPane.showMessageDialog(null, "Seleccione una fila!", "Parking Lot Control", JOptionPane.INFORMATION_MESSAGE);
			}
		});
	
		btnsPanel.add(btnEliminar);
		
		JButton btnEliminarTodo = new JButton("Eliminar Todo");
		btnEliminarTodo.addActionListener((ActionEvent e) -> {
			if(selectedTable == 0) {
				int n = JOptionPane.showConfirmDialog(null,"¿Desea eliminar todos los estacionamietos?" ,"!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
				if(n == JOptionPane.YES_OPTION) {
					ParkingLotControl.getParkingLotControl().getParkingLotsList().clear();
				
					MainFrame.getMainFrame().getContentPane().removeAll();
					MainFrame.getMainFrame().initWestPanel();
					MainFrame.getMainFrame().initNorthPanel();
					MainFrame.getMainFrame().initCenterPanel(MainFrame.getMainFrame().getShowTable());
					MainFrame.getMainFrame().getContentPane().revalidate();
					MainFrame.getMainFrame().getContentPane().repaint();
				}
			}
			else {
				int n = JOptionPane.showConfirmDialog(null,"¿Desea eliminar todos los autos?" ,"!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(n == JOptionPane.YES_OPTION) {
					ParkingLotControl.getParkingLotControl().getCarsList().clear();
				
					MainFrame.getMainFrame().getContentPane().removeAll();
					MainFrame.getMainFrame().initWestPanel();
					MainFrame.getMainFrame().initNorthPanel();
					MainFrame.getMainFrame().initCenterPanel(MainFrame.getMainFrame().getShowTable());
					MainFrame.getMainFrame().getContentPane().revalidate();
					MainFrame.getMainFrame().getContentPane().repaint();
				}
			}
	    });
 	btnsPanel.add(btnEliminarTodo);
 	
 	}
}
 	