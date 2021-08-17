package parkinglotcontrol.models;

import java.io.Serializable;

public class ParkingLot implements Serializable{
	
	private static final long serialVersionUID = -3992151109913311909L;
	private String floor;
	private int parkingNumber;
	private boolean occupancy;
	private OccupancyTime occupancyTime;
	
	//Use this for new Parking lot creation.
	public ParkingLot(String floor, int parkingNumber){
		this.floor = floor;
		this.parkingNumber = parkingNumber;
		this.occupancy = false;
		this.occupancyTime = new OccupancyTime();
	}
	
	//Use this for unknown staying time creation.
	public ParkingLot(String floor, int parkingNumber, boolean occupancy){
		this.floor = floor;
		this.parkingNumber = parkingNumber;
		this.occupancy = occupancy;
		this.occupancyTime = null;
	}
	
	//Use this for full.
	public ParkingLot(String floor, int parkingNumber, boolean occupancy, OccupancyTime occupancyTime){
		this.floor = floor;
		this.parkingNumber = parkingNumber;
		this.occupancy = occupancy;
		this.occupancyTime = occupancyTime;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public int getParkingNumber() {
		return parkingNumber;
	}

	public void setParkingNumber(int parkingNumber) {
		this.parkingNumber = parkingNumber;
	}


	public boolean isOccupancy() {
		return occupancy;
	}


	public void setOccupancy(boolean occupancy) {
		this.occupancy = occupancy;
	}


	public OccupancyTime getOccupancyTime() {
		return occupancyTime;
	}
	
	public void setOccupancyTime(OccupancyTime occupancyTime) {
		this.occupancyTime = occupancyTime;
	}
	
	
	//Use this to set parking lot reservation time.
	public void setReservation(String startDate, String endDate, String startTime, String endTime) {
		this.occupancyTime.setStartDate(startDate);
		this.occupancyTime.setEndDate(endDate);
		this.occupancyTime.setStartTime(startTime);
		this.occupancyTime.setEndTime(endTime);
	}
	

	//Use this to set the parking occupancy, to indicate whether it's free or not
	//if "status" is false time is set to null.
	public void changeOccupancy (boolean status){
		this.setOccupancy(status);
		if(status == false) {
			this.occupancyTime.resetOccupancyTime();
		}
	}
	
	//Use this for table
	public String getStatus() {
		if(this.isOccupancy() == true)
			return "Ocupado";
		else
			return "Libre";
	}
}
