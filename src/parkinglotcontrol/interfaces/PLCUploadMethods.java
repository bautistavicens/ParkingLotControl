package parkinglotcontrol.interfaces;

import parkinglotcontrol.models.Car;
import parkinglotcontrol.models.ParkingLot;

//Use this int ParkingLotControl class to add "Car" object in "carsList" and "ParkingLot" object in "parkingLotsList"
public interface PLCUploadMethods {
	
	public void addCar(Car car);
	public void addParkingLot(ParkingLot parkingLot);
}
