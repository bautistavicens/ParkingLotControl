package parkinglotcontrol.gui.panels.mainframe;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.print.PrinterException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.gui.frames.MainFrame;
import parkinglotcontrol.models.ParkingLot;
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


 	public JTable getTableParking() {
		return tableParking;
	}


	public void setTableParking(JTable tableParking) {
		this.tableParking = tableParking;
	}


	public JTable getTableCar() {
		return tableCar;
	}


	public void setTableCar(JTable tableCar) {
		this.tableCar = tableCar;
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
 		
 		JButton btnEdit = new JButton("Editar");
 		btnEdit.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnEdit.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnEdit.setHorizontalTextPosition(SwingConstants.CENTER);
		btnEdit.setBorderPainted(false);
		btnEdit.setContentAreaFilled(false);
		btnEdit.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/pencil_icon_small.png")));
		
		btnEdit.addMouseListener(new MouseListener() {
			
			public void mouseExited(MouseEvent e) {
				btnEdit.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/pencil_icon_small.png")));
			}
				
			public void mouseEntered(MouseEvent e) {
				btnEdit.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/pencil_icon_big.png")));		
			}

			public void mouseClicked(MouseEvent e) {
		 			
				//Parking lots 0; Cars 1.
				if(selectedTable == 0) {
					try {
						tableParking.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					try {
						tableCar.print();
					} catch (PrinterException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
			}

			public void mousePressed(MouseEvent e) {
				//Pass.
				
			}

			public void mouseReleased(MouseEvent e) {
				//Pass.
				
			}
		});
		
		btnsPanel.add(btnEdit);
		
 		JButton btnDelete = new JButton("Eliminar");
 		btnDelete.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnDelete.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDelete.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDelete.setBorderPainted(false);
		btnDelete.setContentAreaFilled(false);
		btnDelete.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/trash_can_small.png")));
		
		//btnEliminar button MouseListener.
 		btnDelete.addMouseListener(new MouseListener() {
			
			public void mouseExited(MouseEvent e) {
				btnDelete.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/trash_can_small.png")));
			}
				
			public void mouseEntered(MouseEvent e) {
				btnDelete.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/trash_can_big.png")));		
			}
			
			public void mouseClicked(MouseEvent e) {
				if(selectedRow >= 0) {
					
					//Parking lots 0; Cars 1.
					if(selectedTable == 0) {
						
						//Checks if the ParkingLot is occupied.
						if(ParkingLotControl.getParkingLotControl().getParkingLotsList().get(selectedRow).isOccupancy() == false) {
							
							//The program gets the row that user selected and deletes the object (ParkingLot) from the main list.
							ParkingLotControl.getParkingLotControl().getParkingLotsList().remove(selectedRow);
			    		
							//Actualices the MainFrame.
							MainFrame.getMainFrame().getContentPane().removeAll();
							MainFrame.getMainFrame().initWestPanel();
							MainFrame.getMainFrame().initNorthPanel();
							MainFrame.getMainFrame().initCenterPanel(MainFrame.getMainFrame().getShowTable());
							MainFrame.getMainFrame().getContentPane().revalidate();
							MainFrame.getMainFrame().getContentPane().repaint();
						}
						else {
							JOptionPane.showMessageDialog(null,"El estacionamiento está en uso!", "!", JOptionPane.WARNING_MESSAGE);
						}
					}
					else {
						//frees the parking lot.
						ParkingLotControl.getParkingLotControl().getCarsList().get(selectedRow).getParking().changeOccupancy(false);
						
						//The program gets the row that user selected and deletes the objects (Car) from the main list.
						ParkingLotControl.getParkingLotControl().getCarsList().remove(selectedRow);
			    		
						//Actualices the MainFrame.
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
			}

			public void mousePressed(MouseEvent e) {
				//pass
				
			}
				
			public void mouseReleased(MouseEvent e) {
				//pass
					
			}
		});
 		
		btnsPanel.add(btnDelete);
		
		JButton btnDeleteAll = new JButton("Eliminar Todo");
 		btnDeleteAll.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnDeleteAll.setVerticalTextPosition(SwingConstants.BOTTOM);
		btnDeleteAll.setHorizontalTextPosition(SwingConstants.CENTER);
		btnDeleteAll.setBorderPainted(false);
		btnDeleteAll.setContentAreaFilled(false);
		btnDeleteAll.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/trash_can_plus_small.png")));
		
		//btnEliminarTodo button MouseListener.
		btnDeleteAll.addMouseListener(new MouseListener() {
				
			public void mouseExited(MouseEvent e) {
				btnDeleteAll.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/trash_can_plus_small.png")));
			}
				
			public void mouseEntered(MouseEvent e) {
				btnDeleteAll.setIcon(new ImageIcon(MainFrame.class.getResource("/parkinglotcontrol/images/buttons/trash_can_plus_big.png")));		
			}
			
			public void mouseClicked(MouseEvent e) {				
				//Parking lots 0; Cars 1.
				if(selectedTable == 0) {
					int n = JOptionPane.showConfirmDialog(null,"¿Desea eliminar todos los estacionamietos?" ,"!", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
					if(n == JOptionPane.YES_OPTION) {

						ParkingLotControl.getParkingLotControl().getParkingLotsList().clear();
						
						//Actualizes the MainFrame
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
						
						//This goes through the whole "parkingLotsList" and frees every Parking Lot in it.
						for(ParkingLot pl : ParkingLotControl.getParkingLotControl().getParkingLotsList()) {
							
							pl.changeOccupancy(false);
						}
						
						ParkingLotControl.getParkingLotControl().getCarsList().clear();
						
						//Actualices MainFrame
						MainFrame.getMainFrame().getContentPane().removeAll();
						MainFrame.getMainFrame().initWestPanel();
						MainFrame.getMainFrame().initNorthPanel();
						MainFrame.getMainFrame().initCenterPanel(MainFrame.getMainFrame().getShowTable());
						MainFrame.getMainFrame().getContentPane().revalidate();
						MainFrame.getMainFrame().getContentPane().repaint();
					}
				}		
			}

			public void mousePressed(MouseEvent e) {
				//pass
				
			}
				
			public void mouseReleased(MouseEvent e) {
				//pass
					
			}
		});
 	
		btnsPanel.add(btnDeleteAll);
 	}
}
 	