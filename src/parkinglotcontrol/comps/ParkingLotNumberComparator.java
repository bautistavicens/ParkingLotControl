package parkinglotcontrol.comps;

import java.util.Comparator;

import parkinglotcontrol.models.ParkingLot;

public class ParkingLotNumberComparator implements Comparator<ParkingLot>{

	public int compare(ParkingLot o1, ParkingLot o2) {
		if(o1.getIdParking() > o2.getIdParking()) {
			return 1;
		}
		
		return 0;
	}
	
}
