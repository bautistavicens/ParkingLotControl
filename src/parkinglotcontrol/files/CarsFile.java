package parkinglotcontrol.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import parkinglotcontrol.ParkingLotControl;
import parkinglotcontrol.models.Car;

public class CarsFile implements Serializable {

	private static final long serialVersionUID = 8619650411126603775L;

	public void writeFile() throws IOException {
		try {
			
			ObjectOutputStream f1 = new ObjectOutputStream(new FileOutputStream(MainDirectory.PATH+File.separator+"cars.dat"));
			
			f1.writeObject(ParkingLotControl.getParkingLotControl().getCarsList());
			f1.flush();
			f1.close();
		
		}catch(IOException e) {
			
			JOptionPane.showMessageDialog(null, "Error al escribir el archivo cars.dat", "Error.", JOptionPane.ERROR_MESSAGE);
		}
	}
		
	@SuppressWarnings("unchecked")
	public void readFile() throws ClassNotFoundException, FileNotFoundException, IOException {
		try (ObjectInputStream f1 = new ObjectInputStream(new FileInputStream(MainDirectory.PATH+File.separator+"cars.dat"))) {
		
			ParkingLotControl.getParkingLotControl().setCarsList((ArrayList<Car>) f1.readObject());
		
		}catch (FileNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, "No se ha encontrado el archivo \"cars.dat\"", "Error.", JOptionPane.WARNING_MESSAGE);
			
		}catch (IOException e) {
			
			JOptionPane.showMessageDialog(null, "Error al leer el archivo cars.dat!", "Error.", JOptionPane.ERROR_MESSAGE);
			JOptionPane.showMessageDialog(null, e.getMessage()+"\n"+e.getCause(), "Error", JOptionPane.WARNING_MESSAGE);
			
		}catch(ClassNotFoundException e) {
			
			JOptionPane.showMessageDialog(null, "No se ha encontrado la clase correspondiente", "Error.", JOptionPane.ERROR_MESSAGE);
			
		}catch(IndexOutOfBoundsException e) {
			
			JOptionPane.showMessageDialog(null, "Index Out Of Bounds - Array CarsList", "Error.", JOptionPane.ERROR_MESSAGE);
		}
	}
}

