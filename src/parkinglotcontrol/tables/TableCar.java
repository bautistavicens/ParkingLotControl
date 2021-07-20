package parkinglotcontrol.tables;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import parkinglotcontrol.models.Car;

public class TableCar extends AbstractTableModel{

	private static final long serialVersionUID = -5442406979655196745L;
	private String[] nombreCols = {"Id", "Patente", "Dueño", "Piso", "Estacionamiento", "Entrada", "Salida"};
	private List<Car> carsList;
	
	public TableCar(ArrayList<Car> carsList) {
		this.carsList = carsList;
		
		}
    
	public int getRowCount() {
		// El tamaño de las filas será el largo de la lista.
		return carsList.size();
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
				return carsList.get(rowIndex).getIdCar();
			case 1:
				return carsList.get(rowIndex).getLicencePlate();
			case 2:
				return carsList.get(rowIndex).getOwner();
			case 3:
				return carsList.get(rowIndex).getParking().getFloor();
			case 4:
				return carsList.get(rowIndex).getParking().getParkingNumber();
			case 5:
				return carsList.get(rowIndex).getParking().getOccupancyTime().fromDayHour();
			case 6:
				return carsList.get(rowIndex).getParking().getOccupancyTime().toDayHour();
		}																				
		return null;
	}

}

