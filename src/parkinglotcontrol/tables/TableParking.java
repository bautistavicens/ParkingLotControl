package parkinglotcontrol.tables;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import parkinglotcontrol.comps.ParkingLotFloorComparator;
import parkinglotcontrol.comps.ParkingLotNumberComparator;
import parkinglotcontrol.models.ParkingLot;


public class TableParking extends AbstractTableModel{

	private static final long serialVersionUID = -5251804381649491451L;
	private String[] nombreCols = {"Piso", "Estacionamiento", "Estado", "Desde", "Hasta"};
	private List<ParkingLot> parkingLotsList;
	
	public TableParking(ArrayList<ParkingLot> parkingLotsList) {
		this.parkingLotsList = parkingLotsList;

		//Organice Parkings by ID.
		Collections.sort(this.parkingLotsList, new ParkingLotNumberComparator());
		
		//Organice Parkings by floor
		Collections.sort(this.parkingLotsList, new ParkingLotFloorComparator());
		
	}
    
	public int getRowCount() {
		// El tamaño de las filas será el largo de la lista.
		return parkingLotsList.size();
	}

	public int getColumnCount() {
		//Cantidad de columnas será la cantidad de atributos por mostrar.
		return nombreCols.length;
	}
	public String getColumnName(int col) {
		return nombreCols[col];
	}
    public boolean isCellEditable(int row, int column) {
        return false;	
    }
        
	public Object getValueAt(int rowIndex, int columnIndex) {
		switch(columnIndex) {
			case 0:
				return parkingLotsList.get(rowIndex).getFloor();
			case 1:
				return parkingLotsList.get(rowIndex).getParkingNumber();
			case 2:
				return parkingLotsList.get(rowIndex).getStatus();
			case 3:
				return parkingLotsList.get(rowIndex).getOccupancyTime().fromDayHour();
			case 4:
				return parkingLotsList.get(rowIndex).getOccupancyTime().toDayHour();
		}																				
		return null;
	}
}

