package parkinglotcontrol.comps;

import java.util.Comparator;

import parkinglotcontrol.models.ParkingLot;

public class ParkingLotFloorComparator implements Comparator<ParkingLot> {
	
	public int compare(ParkingLot PL1, ParkingLot PL2) {
			return PL1.getFloor().compareTo(PL2.getFloor());
	}
}
