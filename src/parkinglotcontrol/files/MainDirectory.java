package parkinglotcontrol.files;

import java.io.File;

import javax.swing.JOptionPane;

/*El directorio se creará en la carpeta APPDATA*/
public class MainDirectory {
	public static String PATH = System.getenv("APPDATA") + File.separator + "ParkingLotControl";
	private ParkingLotsFile parkingLotsFile;
	private CarsFile carsFile;
	
	public MainDirectory() {
		this.parkingLotsFile = new ParkingLotsFile();
		this.carsFile = new CarsFile();
		
	}

	public void writeFiles() {
		File mainDirectory = new File(PATH);
		try {
			if(mainDirectory.exists()) {
				parkingLotsFile.writeFile();
				carsFile.writeFile();
				
			}
			else {
				if(mainDirectory.mkdir()) {
					parkingLotsFile.writeFile();
					carsFile.writeFile();
				}
				else {
					JOptionPane.showMessageDialog(null, "No se ha podido crear el directorio principal", "Error.", JOptionPane.ERROR_MESSAGE);
				}
			}
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al acceder al directorio principal", "Error.", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public void readFiles() {
		File mainDirectory = new File(PATH);
		try {
			if(mainDirectory.exists()) {
				parkingLotsFile.readFile();
				carsFile.readFile();
			}
			else {
				if(mainDirectory.mkdir()) {
					parkingLotsFile.writeFile();
					carsFile.writeFile();
				}
			}
		}catch(IndexOutOfBoundsException e2) {
			//pass									/*Se ignora este tipo de Excepción, ya que aparecera en caso de que los archivos esten vacios*/
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ha ocurrido un error al acceder al directorio principal", "Error.", JOptionPane.ERROR_MESSAGE);
		}
	}
}