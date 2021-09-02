package parkinglotcontrol;

import java.util.ArrayList;

import javax.crypto.SecretKey;
import javax.swing.JOptionPane;

import parkinglotcontrol.files.MainDirectory;
import parkinglotcontrol.interfaces.PLCUploadMethods;
import parkinglotcontrol.models.Car;
import parkinglotcontrol.models.ParkingLot;
import parkinglotcontrol.models.User;

public class ParkingLotControl implements PLCUploadMethods {
	private User logedUser = null;
	private ArrayList<User> usersList;
	private ArrayList<Car> carsList;
	private ArrayList<ParkingLot> parkingLotsList;
	private MainDirectory mainDirectory;
	private static ParkingLotControl parkingLotControl;
	
	//Don´t use this builder, use ".getParkingLotControl()"
	private ParkingLotControl(){
		usersList = new ArrayList<User>();
		carsList = new ArrayList<Car>();
		parkingLotsList = new ArrayList<ParkingLot>();
		this.setMainDirectory(new MainDirectory());
	}

	
	//use this method to build the class
	public static ParkingLotControl getParkingLotControl() { 
		 if (parkingLotControl==null) {
		 
			 parkingLotControl=new ParkingLotControl();
		 }
		 
		 return parkingLotControl;
	}
	

	public User getLogedUser() {
		return logedUser;
	}


	public void setLogedUser(User logedUser) {
		this.logedUser = logedUser;
	}


	public ArrayList<User> getUsersList() {
		return usersList;
	}


	public void setUsersList(ArrayList<User> usersList) {
		this.usersList = usersList;
	}


	public MainDirectory getMainDirectory() {
		return mainDirectory;
	}


	public void setMainDirectory(MainDirectory mainDirectory) {
		this.mainDirectory = mainDirectory;
	}


	public ArrayList<Car> getCarsList() {
		return carsList;
	}


	public void setCarsList(ArrayList<Car> carsList) {
		this.carsList = carsList;
	}


	public ArrayList<ParkingLot> getParkingLotsList() {
		return parkingLotsList;
	}


	public void setParkingLotsList(ArrayList<ParkingLot> parkingLotsList) {
		this.parkingLotsList = parkingLotsList;
	}
	
	
	//Use this to add a car to the list
	public void addCar(Car car) {
		this.carsList.add(car);
	}
	
	//Use this to add a parking to the list
	public void addParkingLot(ParkingLot parking) {
		this.parkingLotsList.add(parking);
	}
	
	public void registerUser(String username, String email, byte[] password, SecretKey key ) {
		usersList.add(new User(username, email, password, key));
		try {
			ParkingLotControl.getParkingLotControl().getMainDirectory().writeFiles();
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Error de registro de usuario", "Error!", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
}
