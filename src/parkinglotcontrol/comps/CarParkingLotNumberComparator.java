package parkinglotcontrol.comps;

import java.util.Comparator;

import parkinglotcontrol.models.Car;

public class CarParkingLotNumberComparator implements Comparator<Car> {
	
	//Organice Parkings by parkingNumber.
	public int compare(Car o1, Car o2) {
		return o1.getParking().getParkingNumber() - o2.getParking().getParkingNumber();
	}
}
