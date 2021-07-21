package parkinglotcontrol;

import java.util.ArrayList;

import parkinglotcontrol.models.Car;
import parkinglotcontrol.models.ParkingLot;

public class ParkingLotControl {
	private ArrayList<Car> carsList;
	private ArrayList<ParkingLot> parkingLotsList;
	private static ParkingLotControl parkingLotControl;
	
	//Don�t use this builder, use ".getParkingLotControl()"
	private ParkingLotControl(){
		carsList = new ArrayList<Car>();
		parkingLotsList = new ArrayList<ParkingLot>();
		parkingLotsList.add(new ParkingLot("PB", 1));
		carsList.add(new Car(1, "Daniel Vicens" ,"ACX289", parkingLotsList.get(0)));
	}

	
	//use this method to build the class
	public static ParkingLotControl getParkingLotControl() { 
		 if (parkingLotControl==null) {
		 
			 parkingLotControl=new ParkingLotControl();
		 }
		 
		 return parkingLotControl;
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
	public void addParking(ParkingLot parking) {
		this.parkingLotsList.add(parking);
	}
		 
}
